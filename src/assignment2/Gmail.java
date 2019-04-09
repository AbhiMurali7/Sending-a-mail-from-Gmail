package assignment2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Gmail {
private static WebDriver driver;
private static String baseUrl;

public static void main(String[] args) throws Exception {

/* Provide vaild gmail username & password */
String username = "abhimurali7196@gmail.com";
String password = "123456$";
ComposeMail(username,password);
}

public static void ComposeMail(String username,String password) throws Exception {

Date dNow = new Date( );
SimpleDateFormat subjectdate = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
String emailsubject="Current time "+subjectdate.format(dNow).toString();
String tomailid ="abinaya.m@mapolgroup.com";
String mailbody ="This is an auto-generated mail :-)"+"\n\n" + "Regards,"+"\n"+"Abhi";
System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver.exe");
driver = new ChromeDriver();
baseUrl = "https://www.gmail.com";
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

/* Navigate to Gmail */
driver.get(baseUrl);
driver.manage().window().maximize();

/* Enter username and password */
driver.findElement(By.id("identifierId")).sendKeys(username);
driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")).click();
driver.findElement(By.name("password")).sendKeys(password);
driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")).click();

/*Verify login */
String title = "Gmail";
if (driver.getTitle().contains(title)){
System.out.println("Logged in sucessfully !!!"+driver.getTitle());
}
else {
System.out.println("Unable to loggin :-( "+driver.getTitle());
}
/* Compose email */
driver.findElement(By.xpath("//div[@class='z0']/div")).click();

/* Enter email details */
Thread.sleep(1000);
driver.findElement(By.xpath("//td//img[2]")).click();
driver.findElement(By.className("vO")).sendKeys(tomailid);
driver.findElement(By.className("aoT")).sendKeys(emailsubject);
driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).click();
driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys(mailbody);
driver.findElement(By.xpath("//div[text()='Send']")).click();

/* Go to Sent Items and verify */
driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();
if (driver.findElement(By.xpath("//div[@class='y6']//b[text()='"+emailsubject+"']")) != null)
{
System.out.println("Wowww.. Email sent sucessfully!!!");
}
else
{
System.out.println("Failed to send email !!!");
}
Thread.sleep(2000);
driver.quit();
}
}