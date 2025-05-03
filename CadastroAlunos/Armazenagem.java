
/**
 * Write a description of interface armazenagem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Armazenagem
{
   public void inserirAluno(Aluno aluno);
   public void removerAluno(String ra);
   public void mostrarLista();
   public int getTamanho();
   public int buscarRa(String ra);
}
