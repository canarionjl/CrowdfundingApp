package com.joseluis.crowfundingapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.joseluis.crowfundingapp.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CrowdfundingAppInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginRule =
            new ActivityScenarioRule<>(LoginActivity.class); //tener acceso a la actividad para realizar modificación interna

    @Test
    public void LoginAccederComoInvitado() {
       //Se pulsa botón ACCEDER COMO INVITADO
       onView(withId(R.id.textViewGuestAccess)).perform(click());
       //Se debe mostrar el activity con la lista de proyectos (maestro)
       onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));

        //Invitado --> no debe aparecer el icono de añadir a favorito (!!NO ES EXIT ICON!!)
       try {
           onView(withId(R.id.exitIcon)).check(matches(isDisplayed()));
           fail("El icono exitIcon se está mostrando");
       }catch(Exception e){
           assertThat(e,notNullValue());
       }
    }
    @Test
    public void RegistroCorrecto(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTest","passwordTest","emailTest");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Se comprueba texto de SnackBar
        onView((withId(com.google.android.material.R.id.snackbar_text))).check(matches(withText("Se ha registrado correctamente")));
       //Estamos de nuevo en la pantalla 1
        onView(withId(R.id.activity_login)).check(matches(isDisplayed()));
    }

    @Test
    public void LoginCorrecto(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTest","passwordTest","emailTest");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Introducimos usuario y contraseña
        sesionInitProcedure("usuarioTest","passwordTest");
        //Se pasa a la pantalla de la lista de proyectos
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Se debe mostrar el botón de ver favoritos (¡¡NO ES EL EXIT ICON!!)
        onView(withId(R.id.exitIcon)).check(matches(isDisplayed()));

    }

    public void registerProcedure(String username,String password,String email){
        onView(withId(R.id.editTextRegisterUserName)).perform(click());
        onView(withId(R.id.editTextRegisterUserName)).perform(typeText(username));
        pressBack();
        onView(withId(R.id.editTextRegisterPassword)).perform(click());
        onView(withId(R.id.editTextRegisterPassword)).perform(typeText(password));
        pressBack();
        onView(withId(R.id.editTextRegisterEmailAddress)).perform(click());
        onView(withId(R.id.editTextRegisterEmailAddress)).perform(typeText(email));
        pressBack();
    }

    public void sesionInitProcedure (String username,String password){
        onView(withId(R.id.editTextLoginUserName)).perform(click());
        onView(withId(R.id.editTextLoginUserName)).perform(typeText(username));
        pressBack();
        onView(withId(R.id.editTextLoginPassword)).perform(click());
        onView(withId(R.id.editTextLoginPassword)).perform(typeText(password));
        pressBack();
    }
}