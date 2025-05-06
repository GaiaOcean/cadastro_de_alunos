import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;

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

   
}
