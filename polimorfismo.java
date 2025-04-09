//ATIVIDADE 1

// Classe abstrata base
abstract class Forma {
    public abstract double calcularArea();

    public void exibirArea() {
        System.out.println("Área: " + calcularArea());
    }
}

// Classe Círculo
class Circulo extends Forma {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
}

// Classe Quadrado
class Quadrado extends Forma {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}

// Classe Triângulo
class Triangulo extends Forma {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }
}

// Classe principal para teste
public class TesteFormas {
    public static void main(String[] args) {
        Forma f1 = new Circulo(3.0);
        Forma f2 = new Quadrado(4.0);
        Forma f3 = new Triangulo(5.0, 2.0);

        Forma[] formas = {f1, f2, f3};

        for (Forma forma : formas) {
            forma.exibirArea(); // Polimorfismo em ação
        }
    }
}


//ATIVIDADE 2

// Classe abstrata base
abstract class Funcionario {
    protected String nome;

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public abstract double calcularSalario();

    public void exibirSalario() {
        System.out.println(nome + " - Salário: R$ " + calcularSalario());
    }
}

// Funcionário em tempo integral
class FuncionarioTempoIntegral extends Funcionario {
    private double salarioMensal;

    public FuncionarioTempoIntegral(String nome, double salarioMensal) {
        super(nome);
        this.salarioMensal = salarioMensal;
    }

    @Override
    public double calcularSalario() {
        return salarioMensal;
    }
}

// Funcionário de meio período
class FuncionarioMeioPeriodo extends Funcionario {
    private double salarioPorHora;
    private int horasTrabalhadas;

    public FuncionarioMeioPeriodo(String nome, double salarioPorHora, int horasTrabalhadas) {
        super(nome);
        this.salarioPorHora = salarioPorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double calcularSalario() {
        return salarioPorHora * horasTrabalhadas;
    }
}

// Funcionário contratado (freelancer)
class FuncionarioContratado extends Funcionario {
    private double pagamentoPorProjeto;

    public FuncionarioContratado(String nome, double pagamentoPorProjeto) {
        super(nome);
        this.pagamentoPorProjeto = pagamentoPorProjeto;
    }

    @Override
    public double calcularSalario() {
        return pagamentoPorProjeto;
    }
}

// Classe principal para teste
public class Empresa {
    public static void main(String[] args) {
        Funcionario f1 = new FuncionarioTempoIntegral("João", 5000);
        Funcionario f2 = new FuncionarioMeioPeriodo("Maria", 50, 80);
        Funcionario f3 = new FuncionarioContratado("Pedro", 3000);

        Funcionario[] funcionarios = {f1, f2, f3};

        for (Funcionario f : funcionarios) {
            f.exibirSalario(); // Polimorfismo em ação
        }
    }
}
