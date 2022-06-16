package com.joseluis.crowfundingapp;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
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
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

import android.app.Activity;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
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
    public ActivityScenarioRule<LoginActivity> loginScenario =
            new ActivityScenarioRule<>(LoginActivity.class); //tener acceso a la actividad para realizar modificación interna

    @Test
    public void LoginAccederComoInvitado() {
       //Se pulsa botón ACCEDER COMO INVITADO
       onView(withId(R.id.textViewGuestAccess)).perform(click());
       //Se debe mostrar el activity con la lista de proyectos (maestro)
       onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));

        //Invitado --> no debe aparecer el icono de añadir a favorito
       try {
           openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
           fail("El icono 'VER FAVORITO' se está mostrando");
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
        registerProcedure("usuarioTestRC","passwordTest","emailTest@gmail.com");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Estamos de nuevo en la pantalla 1
        onView(withId(R.id.activity_login)).check(matches(isDisplayed()));

    }

    @Test
    public void RegistroIncorrectoInvalidUsername(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTestInvalidUsername","passwordTest","emailTest@gmail.com");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Estamos de nuevo en la pantalla 1
        onView(withId(R.id.textViewIncorrectData)).check(matches(withText("Formato de nombre de usuario incorrecto")));
    }

    @Test
    public void RegistroIncorrectoInvalidEmailWithoutPoint(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTest7","passwordTest","emailTest@gmailcom");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Estamos de nuevo en la pantalla 1
        onView(withId(R.id.textViewIncorrectData)).check(matches(withText("El email debe contener '@' y '.' (punto)")));
    }

    @Test
    public void RegistroIncorrectoInvalidEmailWithoutAt(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTest7","passwordTest","emailTestgmail.com");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Estamos de nuevo en la pantalla 1
        onView(withId(R.id.textViewIncorrectData)).check(matches(withText("El email debe contener '@' y '.' (punto)")));
    }

    @Test
    public void RegistroIncorrectoInvalidPassword(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuario1234","test","emailTest@gmail.com");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Estamos de nuevo en la pantalla 1
        onView(withId(R.id.textViewIncorrectData)).check(matches(withText("La contraseña debe tener 8 caracteres o más")));
    }

    @Test
    public void RegistroIncorrectoRepeatedUsername(){
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se registra por primera vez
        registerProcedure("repeated","passwordTest","emailTest@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());

        //Se vuelve a pantalla de registro y se realiza con las mismas credenciales
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("repeated","passwordTest","emailTest@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        //Texto de username ya existente
        onView(withId(R.id.textViewIncorrectData)).check(matches(withText("El nombre de usuario ya existe")));
    }


    @Test
    public void LoginCorrecto() {
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTestLC", "passwordTest", "emailTest@gmail.com");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Introducimos usuario y contraseña
        sesionInitProcedure("usuarioTestLC", "passwordTest");

        //Se pasa a la pantalla de la lista de proyectos
        onView(withId(R.id.buttonLogin)).perform(click());

        //Se comprueba que estamos en la pantalla de la lista de proyectos
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));

        //Se debe mostrar el botón de ver favoritos
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Ver favoritos")).check(matches(isDisplayed()));
    }
    @Test
    public void LoginIncorrecto() {
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTestLC", "passwordTest", "emailTest@gmail.com");
        //Se pulsa Boton de registrarse
        onView(withId(R.id.buttonRegister)).perform(click());
        //Introducimos usuario y contraseña
        sesionInitProcedure("usuarioTest", "passwordTest");
        //Se indica usuario y/o contraseña incorrecto
        onView(withId(R.id.textViewLoginErrorMessage)).check(matches(withText("Usuario y/o contraseña incorrectos")));
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