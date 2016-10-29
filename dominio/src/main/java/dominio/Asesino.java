package dominio;

import java.util.Random;

public class Asesino extends Casta {

	private double probabilidadDeRobo = 0.2;

	public Asesino(double prob_crit, double evasion, double daņo_crit) {
		super(prob_crit, evasion, daņo_crit);
	}

	public Asesino() {
		super();
	}

	public boolean habilidad1(Personaje caster, Peleable atacado) {// Golpe
																	// Critico
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (atacado.serAtacado((int) (caster.ataque * caster.getCasta().getDaņoCritico())) != 0)
				return true;
		}
		return false;
	}

	public boolean habilidad2(Personaje caster, Peleable atacado) {// Aumentar
																	// Evasion
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (this.getProbabilidadEvitarDaņo() + 0.15 < 0.5)
				this.probabilidadEvitarDaņo += 0.15;
			else
				this.probabilidadEvitarDaņo = 0.5;
			return true;
		}
		return false;
	}

	public boolean habilidad3(Personaje caster, Peleable atacado) {// Robar

		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			Random rnd = new Random();
			if ((rnd.nextDouble() <= this.probabilidadDeRobo + caster.getDestreza() / 1000)
					&& caster.itemsGuardados.size() < 20) {
				Item itemRobado = atacado.serRobado();
				if (itemRobado != null) {
					caster.itemsGuardados.add(itemRobado);
					return true;
				}
			}
		}
		return false;
	}
}
