package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoTest {

    @Test
    public void deveFazerValorDoAluguelDeDoisDiasADoisReaiaODiaSerQuatroReais() {
        Video video1 = VideoBuilder.umVideo().comValorDaDiaria(new BigDecimal(2)).constroi();

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataDeLocacao(LocalDate.now().minusDays(2));
        emprestimo.adiciona(video1);

        emprestimo.setDataDeDevolucao(LocalDate.now());
        emprestimo.calculaValorDoAluguel();

        Assert.assertEquals(new BigDecimal(4), emprestimo.getValorDoAluguel());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveCalcularValorDoAluguelEnquantoNaoFoiEntregue() {
        Emprestimo emprestimo = new Emprestimo();

        emprestimo.calculaValorDoAluguel();
    }

}
