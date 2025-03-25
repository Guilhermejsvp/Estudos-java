// Programa para ler dados de funcionarios e subtrai o imposto para efetuar um aumento salarial
 import java.util.Scanner;
 
 public class Funcionario {
 
     public static void main(String[] args) {
         // Cria o objeto Scanner para ler os dados
         Scanner scanner = new Scanner(System.in);
         
         // Leitura dos dados do funcionário
         System.out.print("Digite o nome do funcionario: ");
         String nome = scanner.nextLine();
 
         System.out.print("Digite o salario bruto do funcionario: ");
         double salarioBruto = scanner.nextDouble();
 
         System.out.print("Digite o valor do imposto do funcionario: ");
         double imposto = scanner.nextDouble();
 
         // Cálculo do salário líquido antes do aumento
         double salarioLiquido = salarioBruto - imposto;
 
         // Exibição dos dados do funcionário antes do aumento
         System.out.println("\n--- Dados do Funcionario ---");
         System.out.println("Nome: " + nome);
         System.out.println("Salario bruto: R$ " + salarioBruto);
         System.out.println("Salario liquido: R$ " + salarioLiquido);
 
         // Solicita a porcentagem de aumento no salário bruto
         System.out.print("\nDigite a porcentagem de aumento do salario bruto: ");
         double porcentagemAumento = scanner.nextDouble();
 
         // Calcula o novo salário bruto com o aumento
         salarioBruto += salarioBruto * (porcentagemAumento / 100);
 
         // Atualiza o salário líquido com o novo valor de salário bruto
         salarioLiquido = salarioBruto - imposto;
 
         // Exibição dos dados do funcionário após o aumento
         System.out.println("\n--- Dados do Funcionario Apos Aumento ---");
         System.out.println("Nome: " + nome);
         System.out.println("Novo salario bruto: R$ " + salarioBruto);
         System.out.println("Novo salario liquido: R$ " + salarioLiquido);
 
         // Fecha o scanner
         scanner.close();
     }
 }
