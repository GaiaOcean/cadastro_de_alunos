
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
        ArquivoTexto.carregarTxt(alunos, "alunos.txt");
        alunos.mostrarLista();
    }
}
