
/**
 * Write a description of class InputException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InputException extends Exception
{
 /**
 * .Verifica se o usuário digitou apenas números
 * @return boolean value
 */
   public boolean validarNumero(String numero){
       try{
          Integer.parseInt(numero);
          return true;
       }catch(NumberFormatException e){
           System.out.println("Por favor informe apenas números");
           return false;
       }
   }
   public boolean validarNomeFile(String nomeArquivo){
       boolean correto = false;
       
       if(nomeArquivo.endsWith(".txt")){
           correto = true;
       }else{
           System.out.println("O nome do arquivo deve terminar com" + ".txt");
       }
       
       return correto;
   }
   
   

}
