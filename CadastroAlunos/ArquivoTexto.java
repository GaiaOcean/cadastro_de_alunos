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
                    writer.write(aluno.getNome() + ";");
                    writer.write(aluno.getIdade() + ";");
                    writer.write(aluno.getRg() + ";");
                    writer.write(aluno.getRa() + ";");
                    writer.write(aluno.getCurso() + ";");
                    writer.write(aluno.getPeriodo() + "\n"); 
                }
            }
            System.out.println("Alunos salvos com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

   public static void carregarTxt(Armazenagem armazenamento, String nomeArquivo) {
        File file = new File(nomeArquivo);
        
        if (!file.exists()) {
            System.out.println("Arquivo: " + nomeArquivo + " inexistente.");
            return;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {  
                String[] tokens = line.split(";");  
                
                if (tokens.length == 6) { //cada aluno tem 6 fields
                    String nome = tokens[0];
                    int idade = Integer.parseInt(tokens[1]);
                    String rg = tokens[2];
                    String ra = tokens[3];
                    String curso = tokens[4];
                    int periodo = Integer.parseInt(tokens[5]);
                    
                    
                    Aluno aluno = new Aluno(nome, idade, rg, ra, curso, periodo);
                    armazenamento.inserirAluno(aluno);
                }
            }
            System.out.println("Alunos carregados com sucesso de: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao carregar alunos: " + e.getMessage());
        }
    }

   
}
