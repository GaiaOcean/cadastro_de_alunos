import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Home extends JFrame {

    private JPanel contentPane;
    private Armazenagem armazenador = null;
    private String nomeArquivo = null;
    private CadAlunos janelaCadastro = null;
    private String RA = null;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Home frame = new Home();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Home() {
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 491, 420); 
        contentPane = new JPanel();
        contentPane.setBackground(new Color(148, 180, 235));
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTituloHome = new JLabel("Cadastro de Aunos Puc-SP");
        lblTituloHome.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTituloHome.setForeground(Color.WHITE);
        lblTituloHome.setBounds(120, 11, 250, 44);
        contentPane.add(lblTituloHome);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(148, 180, 235));
        panel.setBorder(new LineBorder(new Color(86, 136, 219), 11, true));
        panel.setBounds(33, 45, 414, 307);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel imgLista = new JLabel("");
        imgLista.setBounds(43, 60, 145, 145);
        panel.add(imgLista);
        try {
            java.net.URL url = Home.class.getResource("pucsp.png");
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image scaledImage = icon.getImage().getScaledInstance(imgLista.getWidth(),
                                                                      imgLista.getHeight(),
                                                                      Image.SCALE_SMOOTH);
                imgLista.setIcon(new ImageIcon(scaledImage));
            } else {
                imgLista.setText("Imagem não encontrada");
            }
        } catch (Exception e) {
            imgLista.setText("Imagem não encontrada");
        }

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(222, 15, 136, 34);
        panel.add(btnCadastrar);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Calibri", Font.BOLD, 13));
        btnCadastrar.setBackground(new Color(86, 136, 219));
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(armazenador == null){
                    String[] options = {"ArrayNormal", "ArrayLista"};
                
                    String tipo = (String) JOptionPane.showInputDialog(null,
                            "Escolha o tipo de armazenagem",
                            "Tipo de Armazenagem",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
    
                    if (tipo.equals("ArrayNormal")) {
                        String inputQtd = JOptionPane.showInputDialog("Digite a quantidade de alunos:");
                        try {
                            int qtd = Integer.parseInt(inputQtd);
                            armazenador = new ArrayNormal(qtd);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Quantidade inválida.");
                            return;
                        }
                    } else if (tipo.equals("ArrayLista") || tipo.equals(null)){
                        armazenador = new ListaArray();
                    }
    
                    CadAlunos cadastro = new CadAlunos(armazenador);
                    cadastro.setVisible(true);
                    
                }else{
                    janelaCadastro = new CadAlunos(armazenador);
                    janelaCadastro.setVisible(true);
                }
                
        
        }
    });

        JButton btnRemoverAluno = new JButton("Remover Aluno");
        btnRemoverAluno.setBounds(222, 55, 136, 34);
        panel.add(btnRemoverAluno);
        btnRemoverAluno.setForeground(Color.WHITE);
        btnRemoverAluno.setFont(new Font("Calibri", Font.BOLD, 13));
        btnRemoverAluno.setBackground(new Color(86, 136, 219));
        btnRemoverAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
                 RA = JOptionPane.showInputDialog("Digite o RA do aluno que deseja remover:");
                
                 boolean removido = armazenador.removerAluno(RA);
                
                 if(removido){
                       JOptionPane.showMessageDialog(null, "Aluno removido com Sucesso");
                 }else{
                       JOptionPane.showMessageDialog(null, "Ra " + RA + "não encontrado");
                 }
                
            }
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(222, 100, 136, 34);
        panel.add(btnSalvar);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Calibri", Font.BOLD, 13));
        btnSalvar.setBackground(new Color(86, 136, 219));
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo:");
                if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome de arquivo inválido.");
                    return;
                }
                ArquivoTexto arquivo = new ArquivoTexto();
                try{
                    arquivo.salvarTxt(armazenador, nomeArquivo.trim());
                    JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
                }catch(Exception ex ){
                    JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo!");
                }
            }
        });

        JButton btnRecuperar = new JButton("Recuperar");
        btnRecuperar.setBounds(222, 150, 136, 34);
        panel.add(btnRecuperar);
        btnRecuperar.setForeground(Color.WHITE);
        btnRecuperar.setFont(new Font("Calibri", Font.BOLD, 13));
        btnRecuperar.setBackground(new Color(86, 136, 219));
        btnRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo:");
                if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome de arquivo inválido.");
                    return;
                }
                
                ArquivoTexto arquivo = new ArquivoTexto();
                arquivo.carregarTxt(armazenador, nomeArquivo.trim());
                JOptionPane.showMessageDialog(null, "Alunos recuperados com sucesso!");
            }
        });
        
        JButton btnMostrarAlunos = new JButton("Mostrar Alunos");
        btnMostrarAlunos.setBounds(222, 200, 136, 34);
        panel.add(btnMostrarAlunos);
        btnMostrarAlunos.setForeground(Color.WHITE);
        btnMostrarAlunos.setFont(new Font("Calibri", Font.BOLD, 13));
        btnMostrarAlunos.setBackground(new Color(86, 136, 219));
        btnMostrarAlunos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(armazenador != null){
                     armazenador.mostrarLista();
                }else{
                     JOptionPane.showMessageDialog(null, "Não existem alunos cadastrados");
                }
               
            }
        });

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(222, 250, 136, 34);
        panel.add(btnSair);
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Calibri", Font.BOLD, 13));
        btnSair.setBackground(new Color(86, 136, 219));
        btnSair.addActionListener(e -> System.exit(0));
    }
}
