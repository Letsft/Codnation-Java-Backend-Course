package challenge;

import java.util.ArrayList;

public class Estacionamento {

    private ArrayList<Carro> estacionados = new ArrayList<>();
    private static final byte LIMITE = 10;

    public void estacionar(Carro carro) {
        if (carro.getMotorista() == null) {
            throw new EstacionamentoException("Não aceitamos carros autonomos!");
        } else if (carro.getMotorista().getIdade() < 18) {
            throw new EstacionamentoException("Não aceitamos motorista menor de idade!");
        } else if (carro.getMotorista().getPontos() > 20) {
            throw new EstacionamentoException("Não aceitamos motorista com habilitação suspensa!");
        } else if (carrosEstacionados() == LIMITE) {
            for (Carro index : this.estacionados) {
                if (index.getMotorista().getIdade() < 55) {
                    this.estacionados.remove(index);
                    break;
                }
            }
            if (carrosEstacionados() == LIMITE) {
                throw new EstacionamentoException("Estamos sem vagas no momento, por favor retorne mais tarde!");
            }
        }
        System.out.println(carrosEstacionados());
        estacionados.forEach(System.out::println);
        this.estacionados.add(carro);
        carro.toString();
        carro.getMotorista().toString();
    }

    public int carrosEstacionados() {
        return this.estacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return this.estacionados.contains(carro);
    }
}
