
import com.tutorial.arquillian.Credentials;
import com.tutorial.arquillian.LoginController;
import com.tutorial.arquillian.User;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mzych
 */
public class MyDeployment {

    private static final String WEBAPP_SRC = "src/main/webapp";
    
    
    public static WebArchive createLoginScreenDeployment(){
        return ShrinkWrap.create(WebArchive.class, "login.war")
    .addClasses(Credentials.class, User.class, LoginController.class)
    .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
        .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
        "/", Filters.include(".*\\.xhtml$"))
    .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
    .addAsWebInfResource(
        new StringAsset("<faces-config version=\"2.0\"/>"),
        "faces-config.xml");
    }
}
