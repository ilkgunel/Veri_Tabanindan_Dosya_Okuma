import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
@ManagedBean
@RequestScoped
public class Bean implements Filter,Validator {
    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest requset,
                         ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        requset.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(requset, response);
    }
    
private Part dosya;
private String ad;
private String dosyaYolu;

    public void setDosyaYolu(String dosyaYolu) {
        this.dosyaYolu = dosyaYolu;
    }

    public String getDosyaYolu() {
        return dosyaYolu;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }


    public Part getDosya() {
        return dosya;
    }

    public void setDosya(Part dosya) {
        this.dosya = dosya;
    }
    
    public String getFileName(Part part)
    {
        for(String cd:part.getHeader("content-disposition").split(";"))
            if(cd.trim().startsWith("filename")){
                String filename=cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
            }
        return null;
                
    }
    
    public String upload()
    {
        int i=0;
        Connection con=null;
        PreparedStatement ps=null;
        try{
        String dosyaYolu="C:\\Users\\ilkay\\Documents\\NetBeansProjects\\JSFDosyaVeriTabani\\Dosyalar\\";    
        dosya.write(dosyaYolu+getFileName(dosya));
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/insankaynaklari?useUnicode=true&characterEncoding=UTF-8","ilkay","12345");
        ps=con.prepareStatement("INSERT INTO cvler(Ad,CV) VALUES(?,?)");
        ps.setString(1, ad);
        System.out.println("\nAdınız"+ad);
        ps.setString(2,dosyaYolu+getFileName(dosya) );
         i=ps.executeUpdate();
         con.close();
        }
        catch(Exception ex)
        {
            System.out.println("\nHATA MESAJI"+ex);
        }
        
        if(i>0)
            return "basarili.xhtml?faces-redirect=true";
        else
            return "basarisiz.xhtml?faces-redirect=true";
        
    }
    @Override
         public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part part = (Part) value;
        if(part.getSize()>10240000){
            System.out.print(part.getContentType());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dosya boyutu sınırdan büyük.", "Dosya boyutu sınıdan büyük."));
        }
        /*if (!"image/jpeg".equals(part.getContentType())) {
            System.out.print(part.getContentType());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resim Dosyası Değil", "Resim Dosyası Değil"));
        }*/
        if (!"application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(part.getContentType())) {
            System.out.print(part.getContentType());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doc/Docx Dosyası Değil", "Doc/Docx Dosyası Değil"));
        }

    }
         
         public List<Bean> cvListesi()
         {
        int i=0;
        List<Bean> cvListesi=new ArrayList<Bean>();
        Connection con=null;
        PreparedStatement ps=null;
        try{   
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/insankaynaklari?useUnicode=true&characterEncoding=UTF-8","ilkay","12345");
        ps=con.prepareStatement("SELECT * FROM cvler");

         ResultSet rs=ps.executeQuery();
         
         while(rs.next())
         {
             Bean b=new Bean();
             b.setAd(rs.getString("Ad"));
             b.setDosyaYolu(rs.getString("CV"));
             cvListesi.add(b);
         }
         con.close();
        }
        catch(Exception ex)
        {
            System.out.println("\nHATA MESAJI"+ex);
        }
        return cvListesi;
         }
}
