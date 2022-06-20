package com.joseluis.crowfundingapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.fail;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CrowdfundingAppInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginScenario =
            new ActivityScenarioRule<>(LoginActivity.class); //tener acceso a la actividad para realizar modificación interna


    //REGISTER TESTS
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








    //LOGIN TESTS

    @Test
    public void LoginCorrect() {
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
    public void LoginIncorrectUsername() {
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTestLI", "passwordTest", "emailTest@gmail.com");
        //Se pulsa Boton de Register
        onView(withId(R.id.buttonRegister)).perform(click());
        //Introducimos usuario y contraseña
        sesionInitProcedure("usuarioTest", "passwordTest");
        //Se pulsa botón de Login
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se indica usuario y/o contraseña incorrecto
        onView(withId(R.id.textViewLoginErrorMessage)).check(matches(withText("Usuario y/o contraseña incorrectos")));
    }

    @Test
    public void LoginIncorrectPassword() {
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTestLI", "passwordTest", "emailTest@gmail.com");
        //Se pulsa Boton de Register
        onView(withId(R.id.buttonRegister)).perform(click());
        //Introducimos usuario y contraseña
        sesionInitProcedure("usuarioTest", "password");
        //Se pulsa botón de Login
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se indica usuario y/o contraseña incorrecto
        onView(withId(R.id.textViewLoginErrorMessage)).check(matches(withText("Usuario y/o contraseña incorrectos")));
    }

    @Test
    public void LoginIncorrectEmptyUsernameAndPassword() {
        //Click para realizar registro
        onView(withId(R.id.textLoginRegister)).perform(click());
        //Se pasa a la pantalla de registro --> esta es mostrada
        onView(withId(R.id.activity_register)).check(matches(isDisplayed()));
        //Se rellenan los campos adecuadamente
        registerProcedure("usuarioTestLI", "passwordTest", "emailTest@gmail.com");
        //Se pulsa Boton de Register
        onView(withId(R.id.buttonRegister)).perform(click());
        //Introducimos usuario y contraseña
        sesionInitProcedure("", "");
        //Se pulsa botón de Login
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se indica usuario y/o contraseña incorrecto
        onView(withId(R.id.textViewLoginErrorMessage)).check(matches(withText("Usuario y/o contraseña incorrectos")));
    }


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


    //PROJECT LISTS TESTS
    @Test
    public void projectsListIsDisplayedForGuestUsers(){
        //Se pulsa botón ACCEDER COMO INVITADO
        onView(withId(R.id.textViewGuestAccess)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Se checkea el titulo de todos los elementos mostrados para confirmar que todos se muestran

        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.textViewTitleDetailProject))
                .check(matches(withText("CD DE ALBERTO RODRÍGUEZ")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable(R.drawable.nota_musical_icon)));

        onView(withId(R.id.project_list)).perform(scrollToPosition(1));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(1, R.id.textViewTitleDetailProject))
                .check(matches(withText("PARTIDO DE FÚTBOL BENÉFICO")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(1, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.football_ball_icon))));

        onView(withId(R.id.project_list)).perform(scrollToPosition(2));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(2, R.id.textViewTitleDetailProject))
                .check(matches(withText("CARDANO BLOCKCHAIN")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(2, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.technology_icon))));

        onView(withId(R.id.project_list)).perform(scrollToPosition(3));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(3, R.id.textViewTitleDetailProject))
                .check(matches(withText("PARKING-FEST CINEMA MADRID")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(3, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.generic_idea_icon))));

        onView(withId(R.id.project_list)).perform(scrollToPosition(4));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(4, R.id.textViewTitleDetailProject))
                .check(matches(withText("ARDUINO&RASPBERRY IOT START-UP")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(4, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.technology_icon))));
    }

    @Test
    public void projectsListIsDisplayedForLoggedUsers(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());

        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Se checkea el titulo de todos los elementos mostrados y su imagen asociada
        // para confirmar que todos se muestran

        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.textViewTitleDetailProject))
                .check(matches(withText("CD DE ALBERTO RODRÍGUEZ")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable(R.drawable.nota_musical_icon)));

        onView(withId(R.id.project_list)).perform(scrollToPosition(1));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(1, R.id.textViewTitleDetailProject))
                .check(matches(withText("PARTIDO DE FÚTBOL BENÉFICO")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(1, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.football_ball_icon))));

        onView(withId(R.id.project_list)).perform(scrollToPosition(2));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(2, R.id.textViewTitleDetailProject))
                .check(matches(withText("CARDANO BLOCKCHAIN")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(2, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.technology_icon))));

        onView(withId(R.id.project_list)).perform(scrollToPosition(3));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(3, R.id.textViewTitleDetailProject))
                .check(matches(withText("PARKING-FEST CINEMA MADRID")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(3, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.generic_idea_icon))));

        onView(withId(R.id.project_list)).perform(scrollToPosition(4));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(4, R.id.textViewTitleDetailProject))
                .check(matches(withText("ARDUINO&RASPBERRY IOT START-UP")));
        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(4, R.id.imageViewDetailProject))
                .check(matches(EspressoTestsMatchers.withDrawable((R.drawable.technology_icon))));
    }

    @Test
    public void clickOnFirstElementGuestUser(){
        //Se pulsa botón ACCEDER COMO INVITADO
        onView(withId(R.id.textViewGuestAccess)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(0,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("CD DE ALBERTO RODRÍGUEZ"))));
        //Se comprueban los diferentes elementos mostrados
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Combinación de estilos como Rock&Roll y Jazz para crear un disco único que disfrutarás del principio hasta el final ¡No pierdas la oportunidad de apoyar el proyecto y recibir una copia única!")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("19/07/2022 17:30:00")));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(not(isClickable())));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.InvitedFavouriteButton))).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnFirstElementLoggedUser(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(0,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("CD DE ALBERTO RODRÍGUEZ"))));
        //Se comprueban los diferentes elementos mostrados
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Combinación de estilos como Rock&Roll y Jazz para crear un disco único que disfrutarás del principio hasta el final ¡No pierdas la oportunidad de apoyar el proyecto y recibir una copia única!")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("19/07/2022 17:30:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(isClickable()));
    }

    @Test
    public void clickOnSecondElementGuestUser(){
        //Se pulsa botón ACCEDER COMO INVITADO
        onView(withId(R.id.textViewGuestAccess)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(1,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("PARTIDO DE FÚTBOL BENÉFICO"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Partido realizado entre la UD Las Palmas y el UD Moya con el objetivo de recaudar fondos para la Villa de Moya. Se realizará en el estadio García Hernández. Los patrocinadores del evento son tres: Barbería Saul Climent, Spar Supermercados Moya y Bar Cafetería El Palomo. Después del partido los jugadores de la UD Las Palmas realizarán una sesión de firmas y fotos con aquellos aficionados que estén interesados")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("14/08/2022 15:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.InvitedFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(not(isClickable())));
    }

    @Test
    public void clickOnSecondElementLoggedUser(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(1,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("PARTIDO DE FÚTBOL BENÉFICO"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Partido realizado entre la UD Las Palmas y el UD Moya con el objetivo de recaudar fondos para la Villa de Moya. Se realizará en el estadio García Hernández. Los patrocinadores del evento son tres: Barbería Saul Climent, Spar Supermercados Moya y Bar Cafetería El Palomo. Después del partido los jugadores de la UD Las Palmas realizarán una sesión de firmas y fotos con aquellos aficionados que estén interesados")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("14/08/2022 15:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(isClickable()));
    }

    @Test
    public void clickOnThirdElementGuestUser(){
        //Se pulsa botón ACCEDER COMO INVITADO
        onView(withId(R.id.textViewGuestAccess)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(2,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("CARDANO BLOCKCHAIN"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("El objetivo de la Fundación Cardano al iniciar este proyecto no es otro que el de la obtención de recursos económicos para seguir investigando y desarrollando esta red descentralizada de cara a su próximo hard fork")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("08/11/2023 08:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.InvitedFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(not(isClickable())));
    }

    @Test
    public void clickOnThirdElementLoggedUser(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(2,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("CARDANO BLOCKCHAIN"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("El objetivo de la Fundación Cardano al iniciar este proyecto no es otro que el de la obtención de recursos económicos para seguir investigando y desarrollando esta red descentralizada de cara a su próximo hard fork")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("08/11/2023 08:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(isClickable()));
    }

    @Test
    public void clickOnFourthElementGuestUser(){
        //Se pulsa botón ACCEDER COMO INVITADO
        onView(withId(R.id.textViewGuestAccess)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(3,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("PARKING-FEST CINEMA MADRID"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Idea que surge de querer volver a la maravillosa y olvidada idea de los Parking-Cinema. En momentos en los que el distanciamiento social es necesario para algunas personas debido a ser de riesgo frente al Covid-19, queremos que estas también puedan disfrutar con toda seguridad y tranquilidad del cine. Además, ¡¡estaremos recuperando esta traidicional idea que tantas ilusiones brindó en el pasado!!")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("01/12/2022 00:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.InvitedFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(not(isClickable())));
    }

    @Test
    public void clickOnFourthElementLoggedUser(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(3,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("PARKING-FEST CINEMA MADRID"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Idea que surge de querer volver a la maravillosa y olvidada idea de los Parking-Cinema. En momentos en los que el distanciamiento social es necesario para algunas personas debido a ser de riesgo frente al Covid-19, queremos que estas también puedan disfrutar con toda seguridad y tranquilidad del cine. Además, ¡¡estaremos recuperando esta traidicional idea que tantas ilusiones brindó en el pasado!!")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("01/12/2022 00:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(isClickable()));
    }

    @Test
    public void clickOnFifthElementGuestUser(){
        //Se pulsa botón ACCEDER COMO INVITADO
        onView(withId(R.id.textViewGuestAccess)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(4,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("ARDUINO&RASPBERRY IOT START-UP"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Start-Up fundada por algunos alumnos de Ingeniería en Telecomunicaciones e Ingeniería Informática con el objetivo de desarollar diferentes proyectos IoT (como puede ser brazos robóticos) utilizando Arduino (en extensión con placas como la ESP32) y/o Raspberry Pi. Estos proyectos estarán especialmente orientados hacia personas con movilidad reducida. La fecha estimada de nuestro primer proyecto es:")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("19/01/2023 00:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.InvitedFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(not(isClickable())));
    }

    @Test
    public void clickOnFifthElementLoggedUser(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se debe mostrar el activity con la lista de proyectos (maestro)
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));
        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(4,click()));
        //Estamos en pantalla del contenido del projecto (detalle)
        onView(withId(R.id.activity_detail_project)).check(matches(isDisplayed()));
        //Se muestra el título en el Toolbar
        onView(withId(R.id.toolbarProjectDetailActivity)).check(matches(hasDescendant(withText("ARDUINO&RASPBERRY IOT START-UP"))));
        //Se muestra la descripción del proyecto
        onView(withId(R.id.textViewDescriptionDetailProduct)).check(matches(withText("Start-Up fundada por algunos alumnos de Ingeniería en Telecomunicaciones e Ingeniería Informática con el objetivo de desarollar diferentes proyectos IoT (como puede ser brazos robóticos) utilizando Arduino (en extensión con placas como la ESP32) y/o Raspberry Pi. Estos proyectos estarán especialmente orientados hacia personas con movilidad reducida. La fecha estimada de nuestro primer proyecto es:")));
        onView(withId(R.id.textViewDateAndHourDetailProduct)).check(matches(withText("19/01/2023 00:00:00")));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).check(matches(isClickable()));
    }

    @Test
    public void AddingAndDeletingFavouriteItem(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());
        //Se muestra lista de favoritos vacía
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Ver favoritos")).perform(click());
        try {
            onView(new RecyclerViewMatcher(R.id.project_list)
                    .atPositionOnView(0, R.id.textViewTitleDetailProject))
                    .check(matches(isDisplayed()));
            fail("Se está mostrando un elemento de la lista que no corresponde");
        } catch (Exception e){
            assertThat(e,notNullValue());
        }
        //Se vuelve a la lista con todos los proyectos
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Ver favoritos")).perform(click());

        //Click en el primer elemento
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(0,click()));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).perform(click());
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.FavouriteButton))).check(matches(isDisplayed()));
        pressBack();

        //Se muestra lista de favoritos con un elemento
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Ver favoritos")).perform(click());

        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.textViewTitleDetailProject))
                .check(matches(withText("CD DE ALBERTO RODRÍGUEZ")));
        try {
            onView(new RecyclerViewMatcher(R.id.project_list)
                    .atPositionOnView(1, R.id.textViewTitleDetailProject))
                    .check(matches(isDisplayed()));
            fail("Se está mostrando un elemento de la lista que no corresponde");
        } catch (Exception e){
            assertThat(e,notNullValue());
        }
        //Se accede al detalle del item marcado como favorito desde la lista de favoritos
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(0,click()));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.FavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).perform(click());
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        pressBack();

        //Se comprueba que no hay ningun elemento en la lista de favoritos (y por tanto, la lista de favoritos sigue seleccionada)
        try {
            onView(new RecyclerViewMatcher(R.id.project_list)
                    .atPositionOnView(0, R.id.textViewTitleDetailProject))
                    .check(matches(isDisplayed()));
            fail("Se está mostrando un elemento de la lista que no corresponde");
        } catch (Exception e){
            assertThat(e,notNullValue());
        }
    }

    @Test
    public void checkingProjectsRemainStoredInBBDD(){
        //Registro e inicio de sesión
        onView(withId(R.id.textLoginRegister)).perform(click());
        registerProcedure("usuarioTest","password","prueba@gmail.com");
        onView(withId(R.id.buttonRegister)).perform(click());
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());

        //Click en el primer elemento y lo añadimos a favorito
        onView(withId(R.id.project_list)).perform(actionOnItemAtPosition(0,click()));
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.NotFavouriteButton))).check(matches(isDisplayed()));
        onView(withId(R.id.buttonAddFavouriteItem)).perform(click());
        onView(allOf(withId(R.id.buttonAddFavouriteItem),withText(R.string.FavouriteButton))).check(matches(isDisplayed()));
        pressBack();

        //Se muestra lista de favoritos con un elemento
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Ver favoritos")).perform(click());

        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.textViewTitleDetailProject))
                .check(matches(withText("CD DE ALBERTO RODRÍGUEZ")));
        try {
            onView(new RecyclerViewMatcher(R.id.project_list)
                    .atPositionOnView(1, R.id.textViewTitleDetailProject))
                    .check(matches(isDisplayed()));
            fail("Se está mostrando un elemento de la lista que no corresponde");
        } catch (Exception e){
            assertThat(e,notNullValue());
        }

        //Cerramos sesion y se vuelve a pantalla de login(pantalla inicial)
        onView(withId(R.id.exitIcon)).perform(click());
        onView(withId(R.id.activity_login)).check(matches(isDisplayed()));

        //Se vuelve a iniciar sesion
        sesionInitProcedure("usuarioTest","password");
        onView(withId(R.id.buttonLogin)).perform(click());

        //Estamos en la lista de proyectos
        onView(withId(R.id.activity_project_list)).check(matches(isDisplayed()));

        //Se debe mostar la lista de favoritos con el elemento añadido antes de cerrar sesión
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Ver favoritos")).perform(click());

        onView(new RecyclerViewMatcher(R.id.project_list)
                .atPositionOnView(0, R.id.textViewTitleDetailProject))
                .check(matches(withText("CD DE ALBERTO RODRÍGUEZ")));
        try {
            onView(new RecyclerViewMatcher(R.id.project_list)
                    .atPositionOnView(1, R.id.textViewTitleDetailProject))
                    .check(matches(isDisplayed()));
            fail("Se está mostrando un elemento de la lista que no corresponde");
        } catch (Exception e){
            assertThat(e,notNullValue());
        }


    }








    //EXTRA FUNCTIONS

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

//RecyclerViewMatcher by https://gist.github.com/Pierry

class RecyclerViewMatcher {
    private final int recyclerViewId;

    public RecyclerViewMatcher(int recyclerViewId) {
        this.recyclerViewId = recyclerViewId;
    }

    public Matcher<View> atPosition(final int position) {
        return atPositionOnView(position, -1);
    }

    public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            View childView;

            public void describeTo(Description description) {
                String idDescription = Integer.toString(recyclerViewId);
                if (this.resources != null) {
                    try {
                        idDescription = this.resources.getResourceName(recyclerViewId);
                    } catch (Resources.NotFoundException var4) {
                        idDescription = String.format("%s (resource name not found)", new Object[] {
                                Integer.valueOf(recyclerViewId)
                        });
                    }
                }

                description.appendText("with id: " + idDescription);
            }

            public boolean matchesSafely(View view) {

                this.resources = view.getResources();

                if (childView == null) {
                    RecyclerView recyclerView =
                            (RecyclerView) view.getRootView().findViewById(recyclerViewId);
                    if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                        childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                    } else {
                        return false;
                    }
                }

                if (targetViewId == -1) {
                    return view == childView;
                } else {
                    View targetView = childView.findViewById(targetViewId);
                    return view == targetView;
                }
            }
        };
    }
}


class DrawableMatcher extends TypeSafeMatcher<View> {

    private final int expectedId;
    private String resourceName;
    static final int EMPTY = -1;
    static final int ANY = -2;

    DrawableMatcher(int expectedId) {
        super(View.class);
        this.expectedId = expectedId;
    }

    @Override
    protected boolean matchesSafely(View target) {
        if (!(target instanceof ImageView)) {
            return false;
        }
        ImageView imageView = (ImageView) target;
        if (expectedId == EMPTY) {
            return imageView.getDrawable() == null;
        }
        if (expectedId == ANY) {
            return imageView.getDrawable() != null;
        }
        Resources resources = target.getContext().getResources();
        Drawable expectedDrawable = resources.getDrawable(expectedId);
        resourceName = resources.getResourceEntryName(expectedId);

        if (expectedDrawable == null) {
            return false;
        }

        Bitmap bitmap = getBitmap(imageView.getDrawable());
        Bitmap otherBitmap = getBitmap(expectedDrawable);
        return bitmap.sameAs(otherBitmap);
    }

    private Bitmap getBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with drawable from resource id: ");
        description.appendValue(expectedId);
        if (resourceName != null) {
            description.appendText("[");
            description.appendText(resourceName);
            description.appendText("]");
        }
    }
}

class EspressoTestsMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }
}