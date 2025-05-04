/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package atividade.processarautomato;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class ProcessarAutomato {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Automato automato = mapper.readValue(new File("arquivo_do_automato.aut"), Automato.class);

            List<String[]> entradaCsv = lerCSV("arquivo_de_testes.in");
            List<String[]> saidaCsv = new ArrayList<>();

            boolean AFD = ehAFD(automato);
            System.out.println("Tipo de autômato detectado: " + (AFD ? "AFD" : "AFND"));

            for (String[] linha : entradaCsv) {
                String palavra = linha[0];
                int esperado = Integer.parseInt(linha[1]);

                long inicio = System.nanoTime();
                boolean aceitou = AFD
                        ? simulacaoAFD(automato, palavra)
                        : simulacaoAFN(automato, palavra);
                long fim = System.nanoTime();

                saidaCsv.add(new String[]{
                        palavra,
                        String.valueOf(esperado),
                        aceitou ? "1" : "0",
                        String.valueOf(fim - inicio)
                });
            }

            escreverCSV("arquivo_de_saida.out", saidaCsv);
            System.out.println("Processamento finalizado. Arquivo gerado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean ehAFD(Automato automato) {
        Set<String> pares = new HashSet<>();
        for (Transicao t : automato.getTransitions()) {
            String chave = t.getFrom() + ":" + t.getRead();
            if (!pares.add(chave)) return false; 
        }
        return true;
    }

    private static boolean simulacaoAFD(Automato automato, String palavra) {
        int estadoAtual = automato.getInitial();

        for (char c : palavra.toCharArray()) {
            String simbolo = String.valueOf(c);
            boolean encontrou = false;

            for (Transicao t : automato.getTransitions()) {
                if (t.getFrom() == estadoAtual && simbolo.equals(t.getRead())) {
                    estadoAtual = t.getTo();
                    encontrou = true;
                    break;
                }
            }
            if (!encontrou) return false; 
        }

        return automato.getFinal().contains(estadoAtual);
    }

    private static boolean simulacaoAFN(Automato automato, String palavra) {
        return aceitaAFNRec(automato, automato.getInitial(), palavra, 0);
    }

    private static boolean aceitaAFNRec(Automato automato, int estadoAtual, String palavra, int pos) {
        if (pos == palavra.length()) {
            return automato.getFinal().contains(estadoAtual);
        }

        String simbolo = String.valueOf(palavra.charAt(pos));
        for (Transicao t : automato.getTransitions()) {
            if (t.getFrom() == estadoAtual && simbolo.equals(t.getRead())) {
                if (aceitaAFNRec(automato, t.getTo(), palavra, pos + 1)) return true;
            }
        }
        return false;
    }

    private static List<String[]> lerCSV(String nomeArquivo) throws IOException {
        List<String[]> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String linha;
        while ((linha = br.readLine()) != null) {
            linhas.add(linha.split(";"));
        }
        br.close();
        return linhas;
    }

    private static void escreverCSV(String nomeArquivo, List<String[]> linhas) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
        for (String[] linha : linhas) {
            bw.write(String.join(";", linha));
            bw.newLine();
        }
        bw.close();
    }
}

