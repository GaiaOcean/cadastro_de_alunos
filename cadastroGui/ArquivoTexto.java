import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoTexto implements IPersistencia{

    public void salvarTxt(Armazenagem armazenamento, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            if(armazenamento != null){
                Aluno[] alunos = armazenamento.getAlunos();
                int nAluno = 0;
                for (Aluno aluno : alunos) {
                    if (aluno != null) {
                        nAluno++;
                        writer.write(aluno.getNome() + ";");
                        writer.write(aluno.getIdade() + ";");
                        writer.write(aluno.getRg() + ";");
                        writer.write(aluno.getRa() + ";");
                        writer.write(aluno.getCurso() + ";");
                        writer.write(aluno.getPeriodo() + "\n");  // Write on a single line, using semicolons as delimiters
                    }
                }
            }
           
        } catch (IOException e) {
            
        }
    }

   public boolean carregarTxt(Armazenagem armazenamento, String nomeArquivo) {
        File file = new File(nomeArquivo);
        
        if (!file.exists()) {
            return false;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {  // Read line by line
                String[] tokens = line.split(";");  // Split by the semicolon delimiter
                
                if (tokens.length == 6) {  // We expect 6 fields per student
                    String nome = tokens[0];
                    int idade = Integer.parseInt(tokens[1]);
                    String rg = tokens[2];
                    String ra = tokens[3];
                    String curso = tokens[4];
                    int periodo = Integer.parseInt(tokens[5]);
                    
                    // Create a new student object and add it to the storage
                    Aluno aluno = new Aluno(nome, idade, rg, ra, curso, periodo);
                    armazenamento.inserirAluno(aluno);  // Add the student to the storage
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

   
}
