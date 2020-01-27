package com.wafasalaf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
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
import org.springframework.web.client.RestClientException;

import com.wafasalaf.controller.bean.Result;
import com.wafasalaf.mapper.EntrepriseBean;
import com.wafasalaf.service.EntrepriseService;

@RestController
/**
 * 
 * @author OUARDASS ISSMAIL
 *
 */
public class ChercherIceController2 {
	@Autowired
	private EntrepriseService entrepriseService;
	private static Logger logger = LoggerFactory.getLogger(ChercherIceController2.class);

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
	   private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
	        HSSFFont font = workbook.createFont();
	        font.setBold(true);
	        HSSFCellStyle style = workbook.createCellStyle();
	        style.setFont(font);
	        return style;
	    }	
	public static void main(String[] args) throws RestClientException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");
        int rownum = 0;
        Cell cell;
        Row row;
        //
        //HSSFCellStyle style = createStyleForTitle(workbook);
 
        row = sheet.createRow(rownum);
 
        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ICE");
        //cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Raison social");
       // cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("etat");
       // cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("date cration");
       // cell.setCellStyle(style);
        // Bonus
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("adresse");
        
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("rc_ville");
        //cell.setCellStyle(style);
//        HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        List<HttpMessageConverter<?>> httpMessageConverter = Lists.newArrayList();
//        httpMessageConverter.add(stringHttpMessageConverter);
//        restTemplate.setMessageConverters(httpMessageConverter);
//
//        URI targetUrl= UriComponentsBuilder.fromUriString("https://maroc.welipro.com/recherche-avancee?name=&acro=&ice=&rc=120179&cnss=&if=&patente=&v=2&rs=&cp=&cp_max=2035272260000&et=")
//             
//                .build()
//                .toUri();
//
//        org.springframework.http.HttpHeaders headers = new HttpHeaders();
//        Charset utf8 = Charset.forName("UTF-8");
//        MediaType mediaType = new org.springframework.http.MediaType("text", "html", utf8);
//        headers.setContentType(mediaType);
//        headers.set("User-Agent", "mozilla");
//        headers.set("Accept-Language", "ko"); 
//
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        ResponseEntity<HTML> responseEntity = restTemplate.exchange(targetUrl.toURL().toString(), HttpMethod.GET, entity, HTML.class);
//        HTML result = responseEntity.getBody();


long debut = System.currentTimeMillis();
		      String blogUrl=null;
		      org.jsoup.nodes.Document doc=null;
		      for (int i = 10; i < 10000; i++) {
				for (int j = 1; j < 97; j++) {
					  
					blogUrl = "https://maroc.welipro.com/recherche-avancee?name=&acro=&ice=&rc="+i+"&cnss=&if=&patente=&v="+j+"&rs=&cp=&cp_max=2035272260000&et=";
				      doc= Jsoup.connect(blogUrl).get();
				     String ice= doc.select("div.card:nth-child(2) > div:nth-child(3) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2)").text();
				      // EmpNo (A)
			   
			            if ("-".equals(ice)||ice==null||"".equals(ice)){
			            	continue;	
			            }
			            row = sheet.createRow(j);
						 
			            cell = row.createCell(0, CellType.STRING);
			            cell.setCellValue(ice);
			            // EmpName (B)
			            cell = row.createCell(1, CellType.STRING);
			            cell.setCellValue(doc.select("div.card:nth-child(2) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)").text());
			            // Salary (C)
			            cell = row.createCell(2, CellType.STRING);
			            cell.setCellValue(doc.select(".badge").text());
			            // Grade (D)
			            cell = row.createCell(3, CellType.STRING);
			            cell.setCellValue(doc.select("div.row:nth-child(3) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2)").text());
			            // Bonus (E)
	
			            cell = row.createCell(4, CellType.STRING);
			            cell.setCellValue(doc.select("#result > div.card.border-bottom-1.border-bottom-success.rounded-bottom-0 > div.row > div:nth-child(2) > ul > li:nth-child(3)").text());
				  
			            cell = row.createCell(5, CellType.STRING);
			            cell.setCellValue(i+"___"+j);
			
				}
			
			}
		  	File file = new File("C:/c/entreprise.xls");
	        file.getParentFile().mkdirs();
	 
	        FileOutputStream outFile = new FileOutputStream(file);
	        workbook.write(outFile);

System.out.println(System.currentTimeMillis()-debut);
		      
			  
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

		Logger logger = LoggerFactory.getLogger(ChercherIceController2.class);

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