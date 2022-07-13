package br.com.alura.spring.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository repository;
	private Boolean system = true;

	@Autowired
	public CrudCargoService(CargoRepository repository) {
		this.repository = repository;
	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("Digite a operação");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);

		repository.save(cargo);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();
		System.out.println("Digite a descrição: ");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);

		repository.save(cargo);
		System.out.println("Atualizado");
	}

	private void visualizar() {
		Iterable<Cargo> cargos = repository.findAll();
		cargos.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id: ");
		int id = scanner.nextInt();

		repository.deleteById(id);
		System.out.println("Removido");
	}
}
