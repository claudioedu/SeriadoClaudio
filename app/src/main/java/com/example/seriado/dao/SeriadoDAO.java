package com.example.seriado.dao;

import com.example.seriado.modelo.Seriado;

import java.util.ArrayList;
import java.util.List;

public class SeriadoDAO {
    private static List<Seriado> listaSeriado = new ArrayList<>();
    private static Long idGerador = 1L;

    public void incluir(Seriado seriado) {
        seriado.setId(idGerador++);
        listaSeriado.add(seriado);
    }

    public void excluir(Seriado seriado) {
        listaSeriado.remove(seriado);
    }

    public List<Seriado> listar() {
        return listaSeriado;
    }

    public void alterar(Seriado seriado) {
        for (Seriado seriadoAux: listaSeriado) {
            long idSeriado = seriado.getId();
            long idSeriadoAux = seriadoAux.getId();
            if (idSeriado == idSeriadoAux) {
                listaSeriado.remove(seriadoAux);
                listaSeriado.add(seriado);
                break;
            }
        }
    }
}

