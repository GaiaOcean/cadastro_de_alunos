public class InputException extends Exception {
    
    public InputException(String msg) {
        super(msg);
    }

   public static String validarNumeroString(String input,String inputName) throws InputException {
        try {
            Integer.parseInt(input);  
        } catch (NumberFormatException e) {
            throw new InputException("Por favor informe apenas números no campo " + inputName);
        }
        
        return input;
   }
    

    public static String validarNomeFile(String nomeArquivo) {
        if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
            nomeArquivo = nomeArquivo + ".txt";
        }
        
        return nomeArquivo;
    }
}
