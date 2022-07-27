package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private final FuncionarioRepository funcionarioRepository;
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("Digite a operação");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionário nome");
			System.out.println("2 - Busca funcionário nome, data contratação e salario maior");
			System.out.println("3 - Busca funcionário data contratação");
			
			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar: ");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}	
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar: ");
		String nome = scanner.next();

		System.out.println("Qual a data contratação desja pesquisar: ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);

		System.out.println("Qual salário deseja pesquisar: ");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, new BigDecimal(salario), localDate);
		funcionarios.forEach(System.out::println);
	}	
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual a data contratação deseja pesquisar: ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
		funcionarios.forEach(System.out::println);
	}	
}
