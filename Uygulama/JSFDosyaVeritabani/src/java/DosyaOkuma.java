import java.io.FileInputStream;
import java.util.Map;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
@ManagedBean
@RequestScoped
public class DosyaOkuma {
public  static  String bilgi="İlkay Günel";
public String name="İlkay Günel";
public String dosyaYolu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDosyaYolu(String dosyaYolu) {
        this.dosyaYolu = dosyaYolu;
    }

    public String getDosyaYolu() {
        return dosyaYolu;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public String getBilgi() {
        return bilgi;
    }

     public  String dosyaOku()
    {
        try 
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            dosyaYolu = getYolParam(fc);
            FileInputStream fis = new FileInputStream(dosyaYolu);
            //POIFSFileSystem fileSystem = new POIFSFileSystem(fis);            
            XWPFWordExtractor oleTextExtractor =new XWPFWordExtractor(new XWPFDocument(fis));
            System.out.println("DOSYANIN YOLUDUR HA");
            System.out.println("KİŞİ HAKKINDAKİ BİLGİLER"+oleTextExtractor.getText());
            
           bilgi=oleTextExtractor.getText();
           return "icerik.xhtml?faces-redirect=true";
        } 
        catch (Exception e) {
                System.out.println("HATANIZ:"+e);
                return "";
        }
    }

	public String getYolParam(FacesContext fc){
 
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("dosyaYolu");
 
	}
}
