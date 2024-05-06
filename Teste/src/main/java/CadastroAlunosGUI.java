import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroAlunosGUI extends JFrame {
    private JTextField txtNome, txtIdade, txtCurso;
    private JButton btnAdicionar, btnRemover;
    private JTable tblAlunos;
    private DefaultTableModel model;
    private GerenciadorAlunos gerenciador;

    public CadastroAlunosGUI() {
        initComponents();
        gerenciador = new GerenciadorAlunos();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro de Alunos");
        setSize(400, 300);

        txtNome = new JTextField(20);
        txtIdade = new JTextField(10);
        txtCurso = new JTextField(20);

        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");

        model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Idade");
        model.addColumn("Curso");

        tblAlunos = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tblAlunos);

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Código para adicionar aluno
                String nome = txtNome.getText();
                int idade = Integer.parseInt(txtIdade.getText());
                String curso = txtCurso.getText();
                Aluno aluno = new Aluno(nome, idade, curso);
                gerenciador.adicionarAluno(aluno);
                atualizarTabela();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Código para remover aluno
                int rowIndex = tblAlunos.getSelectedRow();
                if (rowIndex != -1) {
                    gerenciador.removerAluno(rowIndex);
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um aluno para remover.");
                }
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome)
                                .addComponent(txtIdade)
                                .addComponent(txtCurso))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(22, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdicionar))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemover))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }

    private void atualizarTabela() {
        List<Aluno> alunos = gerenciador.getAlunos();
        model.setRowCount(0);
        for (Aluno aluno : alunos) {
            Object[] rowData = {aluno.getNome(), aluno.getIdade(), aluno.getCurso()};
            model.addRow(rowData);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroAlunosGUI().setVisible(true);
            }
        });
    }
}
