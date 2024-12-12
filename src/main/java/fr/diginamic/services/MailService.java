package fr.diginamic.services;

import brevo.ApiClient;
import brevo.ApiException;
import brevo.Configuration;
import brevo.auth.ApiKeyAuth;
import brevoApi.TransactionalEmailsApi;
import brevoModel.CreateSmtpEmail;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailTo;
import fr.diginamic.entities.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class MailService {
    @Value("${spring.brevo.key}")
    private String brevoApiKey;

    /**
     * Permet d'envoyer un email de vérfication pour un utilisateur
     * @param utilisateur l'utilisateur concerné
     * @throws ApiException
     */
    public void sendVerificationEmail(Utilisateur utilisateur) throws ApiException {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoApiKey);

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        var params = new HashMap<String, String>();
        params.put("lien", utilisateur.getActivationLink().toString());
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.templateId(3L);
        sendSmtpEmail.to(List.of(new SendSmtpEmailTo().email(utilisateur.getEmail())));
        sendSmtpEmail.params(params);
        CreateSmtpEmail result = apiInstance.sendTransacEmail(sendSmtpEmail);
        System.out.println(result);
    }
}
