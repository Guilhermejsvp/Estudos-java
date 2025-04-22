//SISTEMA DE PEDIDOS ONLINE
import java.util.ArrayList;
import java.util.List;

// Classe abstrata para Usuário
abstract class Usuario {
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    protected String getSenha() {
        return senha;
    }

    public abstract void exibirPerfil();
}

// Classe abstrata para ItemPedido
abstract class ItemPedido {
    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public ItemPedido(String codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double calcularSubtotal() {
        return preco * quantidade;
    }

    public abstract String getTipo();
}

// Classe Cliente (herda de Usuario)
class Cliente extends Usuario {
    private String endereco;
    private String telefone;
    private List<Pedido> historicoPedidos;

    public Cliente(String nome, String email, String senha, String endereco, String telefone) {
        super(nome, email, senha);
        this.endereco = endereco;
        this.telefone = telefone;
        this.historicoPedidos = new ArrayList<>();
    }

    public void adicionarPedido(Pedido pedido) {
        historicoPedidos.add(pedido);
    }

    public List<Pedido> getHistoricoPedidos() {
        return new ArrayList<>(historicoPedidos);
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Perfil do Cliente:");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
    }
}

// Classes concretas de produtos (herdam de ItemPedido)
class Eletronico extends ItemPedido {
    private String marca;
    private int garantiaMeses;

    public Eletronico(String codigo, String nome, double preco, int quantidade, 
                     String marca, int garantiaMeses) {
        super(codigo, nome, preco, quantidade);
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipo() {
        return "Eletrônico";
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Marca: %s, Garantia: %d meses)", 
                           getCodigo(), getNome(), marca, garantiaMeses);
    }
}

class Vestuario extends ItemPedido {
    private String tamanho;
    private String cor;

    public Vestuario(String codigo, String nome, double preco, int quantidade, 
                    String tamanho, String cor) {
        super(codigo, nome, preco, quantidade);
        this.tamanho = tamanho;
        this.cor = cor;
    }

    @Override
    public String getTipo() {
        return "Vestuário";
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Tamanho: %s, Cor: %s)", 
                           getCodigo(), getNome(), tamanho, cor);
    }
}

class Alimento extends ItemPedido {
    private String dataValidade;
    private double peso;

    public Alimento(String codigo, String nome, double preco, int quantidade, 
                   String dataValidade, double peso) {
        super(codigo, nome, preco, quantidade);
        this.dataValidade = dataValidade;
        this.peso = peso;
    }

    @Override
    public String getTipo() {
        return "Alimento";
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Validade: %s, Peso: %.2fkg)", 
                           getCodigo(), getNome(), dataValidade, peso);
    }
}

// Classe Pedido
class Pedido {
    private static int contadorPedidos = 0;
    private final int numeroPedido;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private String status;

    public Pedido(Cliente cliente) {
        this.numeroPedido = ++contadorPedidos;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = "Em processamento";
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
    }

    public double calcularTotal() {
        return itens.stream()
                   .mapToDouble(ItemPedido::calcularSubtotal)
                   .sum();
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void exibirDetalhes() {
        System.out.println("\nDetalhes do Pedido #" + numeroPedido);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Status: " + status);
        System.out.println("\nItens:");
        
        itens.forEach(item -> {
            System.out.printf("- %s (Quantidade: %d, Subtotal: R$%.2f)\n", 
                             item.toString(), item.getQuantidade(), item.calcularSubtotal());
        });
        
        System.out.printf("\nTotal do Pedido: R$%.2f\n", calcularTotal());
    }
}

// Classe principal para demonstração
public class SistemaPedidosOnline {
    public static void main(String[] args) {
        // Criando um cliente
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "senha123", 
                                    "Rua A, 123", "(11) 9999-8888");
        
        // Criando alguns produtos
        Eletronico notebook = new Eletronico("EL001", "Notebook", 3500.00, 1, 
                                           "Dell", 12);
        Vestuario camisa = new Vestuario("VT001", "Camisa Polo", 89.90, 2, 
                                       "M", "Azul");
        Alimento arroz = new Alimento("AL001", "Arroz Integral", 12.50, 3, 
                                    "31/12/2024", 1.0);
        
        // Criando um pedido
        Pedido pedido1 = new Pedido(cliente);
        pedido1.adicionarItem(notebook);
        pedido1.adicionarItem(camisa);
        pedido1.adicionarItem(arroz);
        
        // Exibindo detalhes do pedido
        pedido1.exibirDetalhes();
        
        // Atualizando status do pedido
        pedido1.atualizarStatus("Enviado");
        pedido1.exibirDetalhes();
        
        // Exibindo perfil do cliente
        cliente.exibirPerfil();
    }
}

//GESTÃO DE FUNCIONARIOS
import java.util.ArrayList;
import java.util.List;

// Classe abstrata para Funcionário
abstract class Funcionario {
    private String nome;
    private String matricula;
    private double salarioBase;
    private Departamento departamento;

    public Funcionario(String nome, String matricula, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.salarioBase = salarioBase;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Método abstrato para cálculo de salário
    public abstract double calcularSalario();

    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Base: R$" + salarioBase);
        System.out.println("Departamento: " + 
                         (departamento != null ? departamento.getNome() : "Sem departamento"));
        System.out.println("Salário Total: R$" + calcularSalario());
    }
}

// Classe abstrata para Departamento
abstract class Departamento {
    private String nome;
    private double orcamento;
    private List<Funcionario> funcionarios;

    public Departamento(String nome, double orcamento) {
        this.nome = nome;
        this.orcamento = orcamento;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public List<Funcionario> getFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        funcionario.setDepartamento(this);
    }

    public void removerFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);
        funcionario.setDepartamento(null);
    }

    public double calcularCustoTotal() {
        return funcionarios.stream()
                         .mapToDouble(Funcionario::calcularSalario)
                         .sum();
    }

    public abstract void exibirRelatorio();
}

// Classe concreta para Funcionário Regular
class FuncionarioRegular extends Funcionario {
    public FuncionarioRegular(String nome, String matricula, double salarioBase) {
        super(nome, matricula, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase(); // Funcionários regulares recebem apenas o salário base
    }
}

// Classe concreta para Gerente
class Gerente extends Funcionario {
    private double bonusGestao;
    private List<Funcionario> equipe;

    public Gerente(String nome, String matricula, double salarioBase, double bonusGestao) {
        super(nome, matricula, salarioBase);
        this.bonusGestao = bonusGestao;
        this.equipe = new ArrayList<>();
    }

    public void adicionarMembroEquipe(Funcionario funcionario) {
        equipe.add(funcionario);
    }

    public void removerMembroEquipe(Funcionario funcionario) {
        equipe.remove(funcionario);
    }

    public List<Funcionario> getEquipe() {
        return new ArrayList<>(equipe);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + bonusGestao + (equipe.size() * 50); // Bônus por membro na equipe
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Tamanho da Equipe: " + equipe.size());
        System.out.println("Bônus de Gestão: R$" + bonusGestao);
    }
}

// Classes concretas de Departamentos
class DepartamentoRH extends Departamento {
    public DepartamentoRH(double orcamento) {
        super("Recursos Humanos", orcamento);
    }

    @Override
    public void exibirRelatorio() {
        System.out.println("\nRelatório do Departamento de RH");
        System.out.println("Número de funcionários: " + getFuncionarios().size());
        System.out.println("Custo total: R$" + calcularCustoTotal());
        System.out.println("Orçamento restante: R$" + (getOrcamento() - calcularCustoTotal()));
    }
}

class DepartamentoTI extends Departamento {
    private int projetosAtivos;

    public DepartamentoTI(double orcamento, int projetosAtivos) {
        super("Tecnologia da Informação", orcamento);
        this.projetosAtivos = projetosAtivos;
    }

    @Override
    public void exibirRelatorio() {
        System.out.println("\nRelatório do Departamento de TI");
        System.out.println("Número de funcionários: " + getFuncionarios().size());
        System.out.println("Projetos ativos: " + projetosAtivos);
        System.out.println("Custo total: R$" + calcularCustoTotal());
    }
}

class DepartamentoVendas extends Departamento {
    private double metaVendas;

    public DepartamentoVendas(double orcamento, double metaVendas) {
        super("Vendas", orcamento);
        this.metaVendas = metaVendas;
    }

    @Override
    public void exibirRelatorio() {
        System.out.println("\nRelatório do Departamento de Vendas");
        System.out.println("Número de funcionários: " + getFuncionarios().size());
        System.out.println("Meta de vendas: R$" + metaVendas);
        System.out.println("Custo total: R$" + calcularCustoTotal());
    }
}

// Classe principal para demonstração
public class SistemaGestaoFuncionarios {
    public static void main(String[] args) {
        // Criando departamentos
        DepartamentoRH rh = new DepartamentoRH(50000);
        DepartamentoTI ti = new DepartamentoTI(100000, 5);
        DepartamentoVendas vendas = new DepartamentoVendas(80000, 500000);

        // Criando funcionários
        FuncionarioRegular func1 = new FuncionarioRegular("João Silva", "F001", 3000);
        FuncionarioRegular func2 = new FuncionarioRegular("Maria Souza", "F002", 3200);
        FuncionarioRegular func3 = new FuncionarioRegular("Carlos Oliveira", "F003", 2800);
        
        Gerente gerenteTI = new Gerente("Ana Costa", "G001", 6000, 1000);
        Gerente gerenteVendas = new Gerente("Pedro Santos", "G002", 5500, 800);

        // Adicionando funcionários aos departamentos
        rh.adicionarFuncionario(func1);
        ti.adicionarFuncionario(func2);
        ti.adicionarFuncionario(gerenteTI);
        vendas.adicionarFuncionario(func3);
        vendas.adicionarFuncionario(gerenteVendas);

        // Configurando equipes dos gerentes
        gerenteTI.adicionarMembroEquipe(func2);
        gerenteVendas.adicionarMembroEquipe(func3);

        // Exibindo informações
        System.out.println("=== Informações dos Funcionários ===");
        func1.exibirInformacoes();
        System.out.println();
        func2.exibirInformacoes();
        System.out.println();
        gerenteTI.exibirInformacoes();
        System.out.println();
        gerenteVendas.exibirInformacoes();

        // Exibindo relatórios dos departamentos
        rh.exibirRelatorio();
        ti.exibirRelatorio();
        vendas.exibirRelatorio();

        // Demonstrando polimorfismo
        System.out.println("\n=== Demonstração de Polimorfismo ===");
        List<Funcionario> todosFuncionarios = new ArrayList<>();
        todosFuncionarios.add(func1);
        todosFuncionarios.add(func2);
        todosFuncionarios.add(func3);
        todosFuncionarios.add(gerenteTI);
        todosFuncionarios.add(gerenteVendas);

        double folhaPagamentoTotal = todosFuncionarios.stream()
                                                   .mapToDouble(Funcionario::calcularSalario)
                                                   .sum();
        System.out.println("Folha de pagamento total: R$" + folhaPagamentoTotal);
    }
}
