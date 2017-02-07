package br.com.caelum.casadocodigo.event;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by jefrsilva on 07/02/17.
 */
public class NotificacaoEvent {
    private final RemoteMessage remoteMessage;

    public NotificacaoEvent(RemoteMessage remoteMessage) {
        this.remoteMessage = remoteMessage;
    }
}
