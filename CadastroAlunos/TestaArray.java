
/**
 * Write a description of class TestaArray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestaArray
{
    public static void main(String args[]){
        Armazenagem alunos = new ListaArray();
        Aluno aluno1 = new Aluno("João", 20, "123", "2023001", "Computer Science", 2);
        alunos.inserirAluno(aluno1);
        alunos.mostrarLista();
        Aluno aluno2 =  new Aluno("Grazi", 19, "1111", "727727", "Computer Science", 2);
        alunos.inserirAluno(aluno2);
        alunos.mostrarLista();
        Aluno aluno3 =  new Aluno("Grazi rfyhyhu", 19, "5555","222222", "Computer Science", 2);
        alunos.inserirAluno(aluno3);
        alunos.mostrarLista();
        alunos.removerAluno("727727");
        alunos.mostrarLista();
    }
}
