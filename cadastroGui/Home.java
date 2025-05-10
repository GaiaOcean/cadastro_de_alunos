import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private JPanel contentPane;
    private Armazenagem armazenador = null;
    private String nomeArquivo = null;

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
        contentPane.setBackground(new Color(153, 204, 153));
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTituloHome = new JLabel("LISTA DE AMIGOS");
        lblTituloHome.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTituloHome.setBounds(162, 11, 200, 44);
        contentPane.add(lblTituloHome);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 255, 240));
        panel.setBorder(new LineBorder(new Color(153, 204, 153), 11, true));
        panel.setBounds(33, 45, 414, 307);
        contentPane.add(panel);
        panel.setLayout(null);

       /* JLabel imgLista = new JLabel("");
        imgLista.setBounds(43, 42, 145, 145);
        panel.add(imgLista);
        try {
            imgLista.setIcon(new ImageIcon(Home.class.getResource("")));
        } catch (Exception e) {
            imgLista.setText("Imagem não encontrada");
        }*/

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(222, 30, 136, 34);
        panel.add(btnCadastrar);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Calibri", Font.BOLD, 13));
        btnCadastrar.setBackground(new Color(102, 153, 102));
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = {"ArrayNormal", "ArrayList"};
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
            }
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(222, 80, 136, 34);
        panel.add(btnSalvar);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Calibri", Font.BOLD, 13));
        btnSalvar.setBackground(new Color(102, 153, 102));
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo:");
                if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome de arquivo inválido.");
                    return;
                }
                ArquivoTexto arquivo = new ArquivoTexto();
                arquivo.salvarTxt(armazenador, nomeArquivo.trim());
                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
            }
        });

        JButton btnRecuperar = new JButton("Recuperar");
        btnRecuperar.setBounds(222, 130, 136, 34);
        panel.add(btnRecuperar);
        btnRecuperar.setForeground(Color.WHITE);
        btnRecuperar.setFont(new Font("Calibri", Font.BOLD, 13));
        btnRecuperar.setBackground(new Color(102, 153, 102));
        btnRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo:");
                if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome de arquivo inválido.");
                    return;
                }
                if (armazenador == null || nomeArquivo == null) {
                    JOptionPane.showMessageDialog(null, "Você deve cadastrar e salvar primeiro");
                    return;
                }

                ArquivoTexto arquivo = new ArquivoTexto();
                arquivo.carregarTxt(armazenador, nomeArquivo.trim());
                JOptionPane.showMessageDialog(null, "Alunos recuperados com sucesso!");
            }
        });

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(222, 180, 136, 34);
        panel.add(btnSair);
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Calibri", Font.BOLD, 13));
        btnSair.setBackground(new Color(102, 153, 102));
        btnSair.addActionListener(e -> System.exit(0));
    }
}
