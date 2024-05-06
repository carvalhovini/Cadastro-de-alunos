import java.util.ArrayList;
import java.util.List;

public class GerenciadorAlunos {
    private List<Aluno> alunos;

    public GerenciadorAlunos() {
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    void removerAluno(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
