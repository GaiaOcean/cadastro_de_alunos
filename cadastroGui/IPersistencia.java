
/**
 * Write a description of interface IPersistencia here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface IPersistencia
{
   public void salvarTxt(Armazenagem armazenamento, String nomeArquivo);
   public boolean carregarTxt(Armazenagem armazenamento, String nomeArquivo);
}
