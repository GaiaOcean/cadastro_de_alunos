import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class CadAlunos extends JFrame {

    private JPanel contentPane;
    private JTextField txtNome, txtIdade, txtRg, txtRa, txtCurso, txtPeriodo;
    private JLabel lblSucesso;
    private Armazenagem armazenador;

    public CadAlunos(Armazenagem armazenador) {
        this.armazenador = armazenador;

        setTitle("Cadastro de Aluno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 420);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(102, 153, 204));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("CADASTRO DE ALUNO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
        lblTitulo.setBounds(130, 20, 300, 30);
        contentPane.add(lblTitulo);

        lblSucesso = new JLabel("ALUNO CADASTRADO COM SUCESSO!");
        lblSucesso.setFont(new Font("Calibri", Font.ITALIC, 13));
        lblSucesso.setBounds(130, 60, 250, 20);
        lblSucesso.setVisible(false);
        contentPane.add(lblSucesso);

        int y = 100;
        int spacing = 40;

        txtNome = addLabeledField("Nome:", y);
        txtIdade = addLabeledField("Idade:", y += spacing);
        txtRg = addLabeledField("RG:", y += spacing);
        txtRa = addLabeledField("RA:", y += spacing);
        txtCurso = addLabeledField("Curso:", y += spacing);
        txtPeriodo = addLabeledField("Período:", y += spacing);

        // Painel para botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(130, y += spacing, 250, 40);
        buttonPanel.setBackground(new Color(102, 153, 204));
        contentPane.add(buttonPanel);

        // Botão Cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCadastrar.setBackground(new Color(51, 102, 153));
        btnCadastrar.setForeground(Color.WHITE);
        buttonPanel.add(btnCadastrar);

        // Botão Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(51, 102, 153));
        btnCancelar.setForeground(Color.WHITE);
        buttonPanel.add(btnCancelar);

        // Ação do botão Cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText();
                    int idade = Integer.parseInt(txtIdade.getText());
                    String rg = InputException.validarNumeroString(txtRg.getText(), "RG");
                    String ra = InputException.validarNumeroString(txtRa.getText(), "RA");
                    String curso = txtCurso.getText();
                    int periodo = Integer.parseInt(txtPeriodo.getText());

                    Aluno aluno = new Aluno(nome, idade, rg, ra, curso, periodo);
                    boolean inseriu  = armazenador.inserirAluno(aluno);

                    txtNome.setText("");
                    txtIdade.setText("");
                    txtRg.setText("");
                    txtRa.setText("");
                    txtCurso.setText("");
                    txtPeriodo.setText("");
                    if(inseriu){
                        lblSucesso.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Idade e Período devem ser números válidos.");
                } catch (InputException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (armazenador.getTamanho() > 0) {
                    int escolha = JOptionPane.showConfirmDialog(
                        CadAlunos.this,
                        "Deseja salvar os dados antes de sair?",
                        "Salvar dados",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (escolha == JOptionPane.YES_OPTION) {
                        String nomeArquivo = JOptionPane.showInputDialog(
                            CadAlunos.this,
                            "Digite o nome do arquivo:"
                        );
                        ArquivoTexto arquivo = new ArquivoTexto();
                        arquivo.salvarTxt(armazenador,nomeArquivo);
                        
                    }
                }

                // Voltar ao menu principal
                dispose();
               
            }
        });
    }
    
   
    private JTextField addLabeledField(String label, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Calibri", Font.BOLD, 14));
        lbl.setForeground(Color.WHITE);
        lbl.setBounds(50, y, 100, 20);
        contentPane.add(lbl);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Calibri", Font.PLAIN, 14));
        textField.setBounds(150, y, 250, 25);
        contentPane.add(textField);
        return textField;
    }
}
