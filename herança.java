public class Main {
    public static void main(String[] args) {
        // Criando objetos de cada tipo de veículo
        Veiculo veiculoGenerico = new Veiculo("Genérica", "Modelo X", 2020);
        Carro carro = new Carro("Ford", "Fiesta", 2022, 4, "Flex");
        Bicicleta bicicleta = new Bicicleta("Caloi", "Elite", 2023, 21, true);
        Caminhao caminhao = new Caminhao("Volvo", "FH 540", 2021, 40.5, 6);
        
        // Testando os métodos
        System.out.println("=== Teste do Veículo Genérico ===");
        veiculoGenerico.exibirInformacoes();
        veiculoGenerico.acelerar(50);
        veiculoGenerico.frear(20);
        
        System.out.println("\n=== Teste do Carro ===");
        carro.exibirInformacoes();
        carro.acelerar(80);
        carro.ligarArCondicionado();
        carro.frear(30);
        
        System.out.println("\n=== Teste da Bicicleta ===");
        bicicleta.exibirInformacoes();
        bicicleta.pedalar();
        bicicleta.frear(3);
        
        System.out.println("\n=== Teste do Caminhão ===");
        caminhao.exibirInformacoes();
        caminhao.acelerar(60);
        caminhao.carregar(35.2);
        caminhao.frear(15);
        
        // Demonstração de polimorfismo
        System.out.println("\n=== Demonstração de Polimorfismo ===");
        Veiculo[] frota = new Veiculo[3];
        frota[0] = carro;       // Carro é um Veiculo
        frota[1] = bicicleta;   // Bicicleta é um Veiculo
        frota[2] = caminhao;    // Caminhao é um Veiculo
        
        for (Veiculo v : frota) {
            System.out.println("------------------");
            v.exibirInformacoes(); // Chamada polimórfica
        }
    }
}
