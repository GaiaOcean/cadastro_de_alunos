
/**
 * Write a description of interface armazenagem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Armazenagem
{
   public boolean inserirAluno(Aluno aluno);
   public boolean removerAluno(String ra);
   public void mostrarLista();
   public int getTamanho();
   public int buscarRa(String ra);
   public Aluno[] getAlunos();
   public Aluno getAluno(int i);
}
