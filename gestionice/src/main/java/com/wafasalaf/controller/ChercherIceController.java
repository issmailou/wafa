package com.wafasalaf.controller;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wafasalaf.controller.bean.Result;
import com.wafasalaf.mapper.EntrepriseBean;
import com.wafasalaf.service.EntrepriseService;

@RestController
/**
 * 
 * @author OUARDASS ISSMAIL
 *
 */
public class ChercherIceController {
	@Autowired
	private EntrepriseService entrepriseService;
	private static Logger logger = LoggerFactory.getLogger(ChercherIceController.class);

	/**
	 * service de recherceh ICE
	 * @param rc
	 * @param ville
	 * @return
	 */
	@RequestMapping(value = "/chercherIce/{rc}/{ville}", method = RequestMethod.POST)
	@ResponseBody
	public Result getIce(@PathVariable("rc") Long rc, @PathVariable("ville") String ville) {
		Result resultat=null;
		String ice = entrepriseService.chercherIce(rc, ville);
		String message ="";
		int codeRetour =0;
		if (ice != null) {
			resultat= new Result(ice,codeRetour,message);
		} else {
			Long villeId = entrepriseService.chercherVille(ville.toLowerCase());
			EntrepriseBean entrepriseBean = chercherBySelenium(rc, villeId);
			if (!"0".equals(entrepriseBean.getIce())) {
				entrepriseService.enregistrer(entrepriseBean);
			}else {
				 codeRetour=-1;
				 message = "le code ice est vide";
			}

			ice = entrepriseBean.getIce();
			resultat=new Result(ice,codeRetour,message);
		}

		return resultat ;

	}


	/**
	 * Lancement  selenium
	 * 
	 * @param rc
	 * @param villeId
	 * @return
	 */
	private EntrepriseBean chercherBySelenium(Long rc, Long villeId) {
	
		System.setProperty("webdriver.firefox.bin","C:\\Users\\OUARDASS ISSMAIL\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
	
		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		EntrepriseBean entrepriseBean = new EntrepriseBean();
			driver.get("https://maroc.welipro.com/recherche-avancee?name=&acro=&ice=&rc=" + rc + "&cnss=&if=&patente=&v="+ villeId + "&rs=&cp=&cp_max=2035272260000&et=");
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			String ice = "0";
			if (driver.findElements(By.cssSelector("div.card:nth-child(2) > div:nth-child(3) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2)"))
					.size() != 0) {
				WebElement eltResultat = driver.findElement(By.cssSelector("div.card:nth-child(2) > div:nth-child(3) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2)"));
				ice = eltResultat.getText();
			} else {
				logger.error("Aucun resultat trouvé");
			}

			String adresse = "";
			String text = "";
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			adresse=waitElemntIsPresent(driver,By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/div[2]/div[3]/div[2]/ul/li[3]"));
			text=waitElemntIsPresent(driver,By.cssSelector("div.card:nth-child(2) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)"));
			String etat=waitElemntIsPresent(driver,By.cssSelector(".badge"));
			String dateCreation=waitElemntIsPresent(driver,By.cssSelector("div.row:nth-child(3) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2)")) ;
			entrepriseBean = new EntrepriseBean(ice, rc, text, villeId, adresse,etat,dateCreation);
			return entrepriseBean;
		} catch (Exception e) {
			logger.error("Erreur lors de l'exucution selenium:"+e.getMessage());
			entrepriseBean.setCodeRetour(8);
			entrepriseBean.setMessage("Erreur lors de l'exucution selenium:"+e.getMessage());
			driver.close();
		} finally {
			driver.close();
		}
		return entrepriseBean;

	}
	
	private String waitElemntIsPresent(WebDriver driver, By element) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebElement elemElt = null;
		if (driver.findElements(element).size() != 0) {
			elemElt = driver.findElement(element);
		} else {
			logger.error("Element is Absent");
		}
		if(elemElt==null) {
			return "-";
		}
		return elemElt.getText();
	}
	
	@RequestMapping(value = "/chercherIceByExecel/{societe}", method = RequestMethod.POST)
	public String getIceByExcel(@PathVariable("societe") String nomSociete) {

		Logger logger = LoggerFactory.getLogger(ChercherIceController.class);

		System.setProperty("webdriver.firefox.bin",
				"C:\\Users\\OUARDASS ISSMAIL\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://ice-societe-maroc.com/");
		try {

			WebElement iceInput = driver.findElement(By.id("q"));
			iceInput.clear();
			iceInput.sendKeys(nomSociete);
			Thread.sleep(800);

			driver.findElement(By.className("btn-large")).click();

			WebElement eltResultat = driver.findElement(By.className("card-content"));
			String resultat = eltResultat.getText();
			String ice = resultat.substring(resultat.length() - 15, resultat.length());

			return ice;

		} catch (Exception t) {
			logger.error("Erreur d'appel service");
		} finally {
			driver.close();
		}
		return null;

	}

}