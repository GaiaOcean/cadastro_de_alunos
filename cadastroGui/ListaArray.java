import java.util.ArrayList;
import java.awt.Font;      
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class ListaArray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ListaArray implements Armazenagem
{
   private ArrayList <Aluno> lista;
   
   public ListaArray(){
      setLista(new ArrayList<Aluno>());
   }
   
   private ArrayList <Aluno> getLista(){
       return lista;
   }
   public Aluno[] getAlunos(){
       return lista.toArray(new Aluno[0]);
   }
   
   public int getTamanho(){
       return lista.size();
   }
   
   private void setLista(ArrayList <Aluno> lista){
       this.lista = lista;
   }
   public void inserirAluno(Aluno aluno){
       lista.add(aluno);
   }
   
   public void removerAluno(String ra){
       int pos = buscarRa(ra);

       if(pos != -1){
           lista.remove(pos);
       }
   }
   
   public int buscarRa(String ra){
       int pos = -1;
       int i = 0;
       
       while(pos == -1 && i < lista.size()){
           if(lista.get(i).getRa().equals(ra)){
               pos = i;
           }
           i++;
       }
       
       if(pos == -1){
           System.out.println("Ra "+ra+" não encontrado");
       }
       
       return pos;
   }
   

    public void mostrarLista() {
        if (lista.size() <= 0) {
            JOptionPane.showMessageDialog(null, "Não existem alunos cadastrados.");
            return;
        }
    
        StringBuilder list = new StringBuilder();
        int nAluno = 0;
    
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) != null) {
                nAluno++;
                list.append("----------------- Aluno ").append(nAluno).append(" ----------------\n");
                list.append(lista.get(i).toString()).append("\n\n");
            }
        }
    
        // Criando janela com a mesma aparência do CadAlunos
        JFrame listaFrame = new JFrame("Lista de Alunos");
        listaFrame.setSize(500, 450);
        listaFrame.setBounds(100, 100, 500, 450);// Centraliza na tela
        listaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        JPanel panel = new JPanel();
        panel.setBackground(new Color(102, 153, 204));
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel titulo = new JLabel("LISTA DE ALUNOS", JLabel.CENTER);
        titulo.setFont(new Font("Calibri", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        panel.add(titulo, BorderLayout.NORTH);
    
        JTextArea textArea = new JTextArea(list.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        textArea.setBackground(Color.WHITE);
    
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        listaFrame.setContentPane(panel);
        listaFrame.setVisible(true);
    }
}



   
   
   
   
   
