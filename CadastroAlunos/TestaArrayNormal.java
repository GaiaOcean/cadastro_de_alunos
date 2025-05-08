
/**
 * Write a description of class TestaArrayNormal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestaArrayNormal
{
    // instance variables - replace the example below with your own
   public static void main(String args[]){
       int capacidade  = 3;
       Armazenagem alunos = new ArrayNormal(capacidade);
       ArquivoTexto arquivo = new ArquivoTexto();
       /*Aluno aluno1 = new Aluno("João", 20, "123", "2023001", "Computer Science", 2);
       alunos.inserirAluno(aluno1);
       Aluno aluno2 =  new Aluno("Grazi", 19, "1111", "727727", "Computer Science", 2);
       alunos.inserirAluno(aluno2);
       Aluno aluno3 =  new Aluno("Grazi rfyhyhu", 19, "5555","222222", "Computer Science", 2);
       alunos.inserirAluno(aluno3);
       alunos.mostrarLista();
       System.out.println("Removendo Grazi");
       alunos.removerAluno("727727");
       alunos.mostrarLista();
       System.out.println("Depois de Ter removido");
       System.out.println("Adiiconando um  novo aluno");
       Aluno aluno4 = new Aluno("Antonio", 19, "1111", "838293", "Computer Science",2);
       alunos.inserirAluno(aluno4);*/
       //ArquivoTexto.salvarTxt(alunos,"alunos2.txt");
       arquivo.carregarTxt(alunos,"alunos2.txt");
       alunos.mostrarLista();
       
    }
}
