import java.awt.Font;      
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class ArrayDIn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayNormal implements Armazenagem
{
    private Aluno[] lista;
    private int capacidade;
    private int cadastrados;
    
    
    public ArrayNormal(int capacidade){
       this.capacidade = capacidade;
       this.lista = new Aluno[capacidade];
       this.cadastrados = 0;
    }
    
    public  Aluno[] getAlunos(){
        int arraySize = getTamanho();
        Aluno[] alunos = new Aluno[arraySize];
        for (int i = 0; i <arraySize; i++) {
            alunos[i] = lista[i]; 
        }
        return  alunos;
    }
    
    public int getTamanho(){
        return cadastrados;
    }
    
    public boolean inserirAluno(Aluno aluno){
        
        if(capacidade == cadastrados){
            JOptionPane.showMessageDialog(null, "Não há mais espaço para cadastro");
            return false;
        }else{
           lista[cadastrados] = aluno;
           cadastrados++;
           //System.out.println("Aluno " + aluno.getNome() + " cadastrado com sucesso.");
        }
        return true;
    }
    public boolean removerAluno(String ra){
        int pos = buscarRa(ra);
        
        if(pos == -1){
             return false;
        }else{
            
            for(int i = pos; i < cadastrados-1; i++){
                lista[i] = lista[i+1];
            }
            cadastrados--;
        }
        
        return true;
    }
    
    public int buscarRa(String ra){
        int pos = -1;
        int i = 0;
        while(pos == -1 && i < cadastrados){
            if(lista[i].getRa().equals(ra)){
                pos = i;
            }
            i++;
        }
        return pos;
    }
    
   public void mostrarLista() {
    if (cadastrados <= 0) {
        JOptionPane.showMessageDialog(null, "Não existem alunos cadastrados.");
        return;
    }

    StringBuilder list = new StringBuilder();
    int nAluno = 0;

    for (int i = 0; i < cadastrados; i++) {
        if (lista[i] != null) {
            nAluno++;
            list.append("----- Aluno ").append(nAluno).append(" -----\n");
            list.append(lista[i].toString()).append("\n\n");
        }
    }

    // Janela para exibir a lista
    JFrame listaFrame = new JFrame("Lista de Alunos");
    listaFrame.setSize(500, 450);
    listaFrame.setBounds(100, 100, 500, 450);  // Centraliza na tela
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
    textArea.setBackground(new Color(255, 255, 255));
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    panel.add(scrollPane, BorderLayout.CENTER);

    listaFrame.setContentPane(panel);
    listaFrame.setVisible(true);
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

