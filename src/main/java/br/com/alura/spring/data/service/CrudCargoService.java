package br.com.alura.spring.data.service;

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
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Alterar");

			int action = scanner.nextInt();

			if (action == 1) {
				salvar(scanner);
			} else if (action == 2) {
				atualizar(scanner);
			} else {
				system = false;
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
		System.out.println("Id do cargo para atualizar: ");
		int id = scanner.nextInt();

		Cargo cargo = new Cargo();
		cargo = repository.findById(id).get();

		System.out.println("Digite a descrição: ");
		String descricao = scanner.next();
		cargo.setDescricao(descricao);
		repository.save(cargo);
		System.out.println("Atualizado");
	}

}
