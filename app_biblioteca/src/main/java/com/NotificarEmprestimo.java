package com;

public class NotificarEmprestimo implements InterNotificaçao {
    @Override
    public void Notificar(Usuario usuario){
    System.out.println("\nNotificação de empréstimo enviado para o e-mail: " + usuario.getEmail());
    }
}
