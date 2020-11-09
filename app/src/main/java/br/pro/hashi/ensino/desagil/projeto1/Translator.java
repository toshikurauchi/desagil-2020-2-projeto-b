package br.pro.hashi.ensino.desagil.projeto1;

import java.util.HashMap;
import java.util.LinkedList;

// Não é permitido mudar nada nessa classe
// exceto o recheio dos três métodos.

public class Translator {
    private final Node root;
    private final HashMap<Character, Node> map;


    // Você deve mudar o recheio deste construtor,
    // de acordo com os requisitos não-funcionais.
    public Translator() {
        map = new HashMap<>();
        //char[] alphanumeric = " etianmsurwdkgohvf!l@pjbxcyzq#$54%3&*(2)[]{}<>16ç;:?/|,7.£¢8+90".toCharArray();
        char[] alphanumeric = " etianmsurwdkgohvf l pjbxcyzq  54 3   2       16       7   8 90".toCharArray();
        root = new Node(alphanumeric[0]);
        map.put(alphanumeric[0], root);
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        for(int i=1;i<alphanumeric.length;i++){
            nodes.add(new Node(alphanumeric[i]));
        }
        for(int i=0;i<nodes.size()/2;i++){
            Node node = nodes.get(i);
            Node leftChild = nodes.get(2*i+1);
            Node rightChild = nodes.get(2*i+2);
            leftChild.setParent(node);
            rightChild.setParent(node);
            node.setLeft(leftChild);
            node.setRight(rightChild);
        }
        for(int i=0;i<nodes.size();i++){
            map.put(nodes.get(i).getValue(), nodes.get(i));
        }
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public char morseToChar(String code) {
        return ' ';
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public String charToMorse(char c) {
        return " ";
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public LinkedList<String> getCodes() {
        return new LinkedList<>();
    }

}
