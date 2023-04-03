import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class telaInicial extends JFrame {
    private JPanel pnlCadastro;
    private JTextField txtCpfCnpj;
    private JButton btnCadastrarSe;
    private JLabel lblCpfCnpj;
    private JLabel lblCep;
    private JLabel lblNomeDaRua;
    private JLabel lblNumeroDaResidencia;
    private JLabel lblComplemento;
    private JLabel lblCidade;
    private JLabel lblEstado;
    private JLabel lblEmail;
    private JLabel lblSenha;
    private JLabel lblSenhaNovamente;
    private JPasswordField pswSenha;
    private JPasswordField pswSenhaNovamente;
    private JTextField txtCep;
    private JTextField txtNomeDaRua;
    private JTextField txtNumeroDaResidencia;
    private JTextField txtComplemento;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtEmail;
    private JButton btnVoltar;
    private JPanel lbltexto1;
    private JLabel lbltexto2;
    private JLabel lbltexto3;
    private JLabel lbltexto5;
    private JCheckBox euConcordoComOsCheckBox;
    private JTextField txtNome;
    private JLabel lblNome;

    final String URL = "jdbc:mysql://localhost:3306/cadastroCliente";
    final String USER = "root";
    final String PASSWORD = "root99";

    final String CadastrarSe = "INSERT INTO cadastro(Nome, CpfCnpj, Cep, NomeDaRua, NumeroDaResidencia, Complemento, Cidade, Estado, Email, Senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public telaInicial() {
        IniciarComponentes();
        Conecta();

    }

    public void Conecta() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado");

            final PreparedStatement stmtCadastrarSe;
            stmtCadastrarSe = connection.prepareStatement(CadastrarSe);

            btnCadastrarSe.addActionListener(new ActionListener() {
                @Override


                public void actionPerformed(ActionEvent e) {

                    String nome = txtNome.getText();
                    String cpfCnpjStr = txtCpfCnpj.getText();
                    String cep = txtCep.getText();
                    String nomeDaRua = txtNomeDaRua.getText();
                    String numeroDaResidencia = txtNumeroDaResidencia.getText();
                    String complemento = txtComplemento.getText();
                    String cidade = txtCidade.getText();
                    String estado = txtEstado.getText();
                    String email = txtEmail.getText();
                    String senha = pswSenha.getText();

                    try {
                        int cpfCnpj = Integer.parseInt(cpfCnpjStr);
                        stmtCadastrarSe.setString(1, nome);
                        stmtCadastrarSe.setInt(2, cpfCnpj);
                        stmtCadastrarSe.setString(3, cep);
                        stmtCadastrarSe.setString(4, nomeDaRua);
                        stmtCadastrarSe.setString(5, numeroDaResidencia);
                        stmtCadastrarSe.setString(6, complemento);
                        stmtCadastrarSe.setString(7, cidade);
                        stmtCadastrarSe.setString(8, estado);
                        stmtCadastrarSe.setString(9, email);
                        stmtCadastrarSe.setString(10, senha);

                        if (euConcordoComOsCheckBox.isSelected()) {
                        stmtCadastrarSe.executeUpdate();
                        System.out.println("Cadastro realizado com sucesso!");
                        JOptionPane.showMessageDialog(btnCadastrarSe, "Cadastro realizado com sucesso!");
                        txtNome.setText("");
                        txtCpfCnpj.setText("");
                        txtCep.setText("");
                        txtNomeDaRua.setText("");
                        txtNumeroDaResidencia.setText("");
                        txtComplemento.setText("");
                        txtCidade.setText("");
                        txtEstado.setText("");
                        txtEmail.setText("");
                        pswSenha.setText("");
                        pswSenhaNovamente.setText("");

                        } else {
                            JOptionPane.showMessageDialog(euConcordoComOsCheckBox, "Marque a caixa Eu concordo com os termos de uso");
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("o CPF informado não é um numero");
                    } catch (Exception ex) {
                        System.out.println("Erro ao inserir dados no banco");
                    }

                }
            });
        } catch (Exception ex) {

            System.out.println("Erro ao conectar banco de dados");

        }

    }


   public void IniciarComponentes(){
       setTitle("cadastro de cliente");
       setSize(600, 600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setContentPane(pnlCadastro);
       setVisible(true);
   }
        public static void main (String[]args){
            telaInicial telaInicial = new telaInicial();
        }
}



