
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
        ArquivoTexto arquivo = new ArquivoTexto();
        /*Aluno aluno1 = new Aluno("Grazielle",80,"662113","666888","CC",3);
        alunos.inserirAluno(aluno1);
        Aluno aluno2 = new Aluno("Giullia",80,"6221","613388","CC",3);
        alunos.inserirAluno(aluno2);
        Aluno aluno3 = new Aluno("Steh",12,"66666","1111","CC",3);
        alunos.inserirAluno(aluno3);*/
        //ArquivoTexto.salvarTxt(alunos, "alunos.txt");
        arquivo.carregarTxt(alunos, "alunos.txt");
        alunos.mostrarLista();
        //alunos.mostrarLista();
    }
}
