import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoTexto {

    
      
    public static void salvarTxt(Armazenagem armazenamento, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            Aluno[] alunos = armazenamento.getAlunos();
            int nAluno = 0;
            for (Aluno aluno : alunos) {
                if (aluno != null) {
                    nAluno++;
                    writer.write("----------------- Aluno " + nAluno + "----------------\n");
                    writer.write("Nome: " + aluno.getNome() + ", ");
                    writer.write("Idade: " + aluno.getIdade() + ", ");
                    writer.write("RG: " + aluno.getRg() + ", ");
                    writer.write("RA: " + aluno.getRa() + ", ");
                    writer.write("Curso: " + aluno.getCurso() + ", ");
                    writer.write("Período: " + aluno.getPeriodo() + "\n\n");
                }
            }
            System.out.println("Alunos salvos com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    
    public static void carregarTxt(Armazenagem armazenamento, String nomeArquivo){
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        boolean repetir;
        
        do {
            repetir = false;
            file = new File(nomeArquivo);
            if (!file.exists()) { // Verifica se o qruivo existe
                System.out.println("Arquivo:" + nomeArquivo + " inexistente, deseja tentar de novo?(s/n): ");
            }
        } while(repetir);
        
         try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch(FileNotFoundException ex) {
            System.out.println("Arquivo inexistente: " + nomeArquivo);
            return;
        } catch(Exception ex) {
            System.out.println("Erro inesperado ao tentar abrir o arquivo: " + nomeArquivo);
            ex.printStackTrace();
            return;
        }
        
        try {
            
            String line = br.readLine();
            while(line != null) { // Enquanto existir linha no arquivo
                String separadores = "\n"+ "|"; //"\t\n\r\f"+ "|";
                StringTokenizer st = new StringTokenizer(line, separadores);

                // inteiro
                if (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    int a = Integer.parseInt(tok);
                    System.out.println("Leu o inteiro: " + a + " do disco");
                } 

                // String    
                if (st.hasMoreTokens()) {
                    String s = st.nextToken();
                    System.out.println("Leu o String: " + s + " do disco");
                }

                // double
                if (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    double b = Double.parseDouble(tok);
                    System.out.println("Leu o double: " + b + " do disco");
                }

                line = br.readLine(); // Le a proxima linha
            }

        }catch(Exception e){
             System.out.println("ERRO\n");
        }
    }
    

   
}
