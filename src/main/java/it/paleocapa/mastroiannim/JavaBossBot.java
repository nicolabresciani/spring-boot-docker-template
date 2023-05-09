package it.paleocapa.mastroiannim;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class JavaBossBot extends TelegramLongPollingBot {

	private static final Logger LOG = LoggerFactory.getLogger(JavaBossBot.class);

	private String botUsername;
	private static String botToken;
	private static JavaBossBot instance;

	public static JavaBossBot getJavaBossBotInstance(String botUsername, String botToken){
		if(instance == null) {
			instance = new JavaBossBot();
			instance.botUsername = botUsername;
			JavaBossBot.botToken = botToken;
		}
		return instance;
	}

	private JavaBossBot(){
		super(botToken);
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
	
	@Override
	public String getBotUsername() {
		return botUsername;
	}
	LinkedList<String> l = new LinkedList<String>();
	public String[] cibo = {"pizza", "hamburger", "insalata", "pasta"};
	public String[] bibite = {"coca-cola", "birra", "acqua", "tè-limone", "tè-pesca"};

	public String sceltaCibo = " ";
	public String sceltaBibite = " ";
	@Override
	public void onUpdateReceived(Update update) {
		String command = update.getMessage().getText();

        if(command.equals("/run")){
            String message = "cosa vuoi ordinare ?";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            try {
                execute(response);
            }catch (TelegramApiException E){
                E.printStackTrace();
            }
        }
	}
}
