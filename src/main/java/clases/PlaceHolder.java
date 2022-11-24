package clases;
//java
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//extension larga
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

/**
 * Clase encargada de los placeholders en los campos de texto.
 * 
 * @author unknown
 */
public class PlaceHolder{
    protected JTextField textField;
    protected JTextArea textArea;
    protected Color holderColor;
    protected Color singleColor;
    protected String text;
    protected boolean italic;
    protected String font;
    protected int size;

    public PlaceHolder(final JTextArea textArea,final String text){
        this.inicialize();
        this.textArea=textArea;
        this.text=text;
        textArea.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                PlaceHolder.this.showTextArea(textArea,text,textArea.getText().trim().length());
            }
        });
        
        textArea.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent evt){
                PlaceHolder.this.hideTextArea(textArea,text);
            }
        });
        
        this.showTextArea(textArea,text,textArea.getText().trim().length());
    }

    public PlaceHolder(final JTextArea textArea,Color holderColor,Color singleColor,final String text,boolean italic,String font,int size){
        this.textArea=textArea;
        this.holderColor=holderColor;
        this.singleColor=singleColor;
        this.text=text;
        this.italic=italic;
        this.font=font;
        this.size=size;
        textArea.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                PlaceHolder.this.showTextArea(textArea,text,textArea.getText().trim().length());
            }
        });
        
        textArea.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent evt){
                PlaceHolder.this.hideTextArea(textArea,text);
            }
        });
        
        this.showTextArea(textArea,text,textArea.getText().trim().length());
    }

    public PlaceHolder(final JTextField textField,Color holderColor,Color singleColor,final String text,boolean italic,String font,int size){
        this.textField=textField;
        this.holderColor=holderColor;
        this.singleColor=singleColor;
        this.text=text;
        this.italic=italic;
        this.font=font;
        this.size=size;
        textField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                PlaceHolder.this.showText(textField,text,textField.getText().trim().length());
            }
        });
        
        textField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent evt){
                PlaceHolder.this.hideText(textField,text);
            }
        });
        
        this.showText(textField,text,textField.getText().trim().length());
    }
    
    public PlaceHolder(final JTextField textField,final String text){
        this.inicialize();
        this.textField=textField;
        this.text=text;
        textField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                PlaceHolder.this.showText(textField,text,textField.getText().trim().length());
            }
        });
        
        textField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent evt){
                PlaceHolder.this.hideText(textField,text);
            }
        });
        
        this.showText(textField,text,textField.getText().trim().length());
    }
    
    public void inicialize(){
        this.holderColor=Color.LIGHT_GRAY;
        this.singleColor=Color.BLACK;
        this.italic=false;
        this.font="Tahoma";
        this.size=12;
    }
    
    public Color getHolderColor(){
        return this.holderColor;
    }
    
    public void setHolderColor(Color holderColor){
        this.holderColor=holderColor;
    }
    
    public Color getSingleColor(){
        return this.singleColor;
    }
    
    public void setSingleColor(Color singleColor){
        this.singleColor=singleColor;
    }
    
    public String getText(){
        return this.text;
    }
    
    public void setText(String text){
        this.text=text;
    }
    
    public boolean getItalic(){
        return this.italic;
    }
    
    public void setItalic(boolean italic){
        this.italic=italic;
    }
    
    public String getFont(){
        return this.font;
    }
    
    public void setFont(String font){
        this.font=font;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void setSize(int size){
        this.size=size;
    }
    
    public void showText(JTextField textField,String text,int size){
        if(size==0){
            textField.setText(text);
            textField.setForeground(this.holderColor);
            if(this.italic){
                textField.setFont(new Font(this.font,2,this.size));
            }else{
                textField.setFont(new Font(this.font,0,this.size));
            }
        }
    }
    
    public void showTextArea(JTextArea textArea,String text,int size){
        if(size==0){
            textArea.setText(text);
            textArea.setForeground(this.holderColor);
            if(this.italic){
                textArea.setFont(new Font(this.font,2,this.size));
            }else{
                textArea.setFont(new Font(this.font,0,this.size));
            }
        }
    }
    
    public void hideText(JTextField textField,String text){
        if(textField.getText().equals(text)){
            textField.setText("");
            textField.setForeground(this.singleColor);
        }else{
            textField.setForeground(this.singleColor);
        }
    }
    
    public void hideTextArea(JTextArea textArea,String text){
        if(textArea.getText().equals(text)){
            textArea.setText("");
            textArea.setForeground(this.singleColor);
        }else{
            textArea.setForeground(this.singleColor);
        }
    }
}