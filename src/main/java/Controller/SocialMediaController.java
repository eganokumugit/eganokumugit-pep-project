package Controller;
import Model.*;
import Service.*;
import Service.AccountService;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    AccountService accService;
    MessageService msgService;
    public SocialMediaController()
    {
        accService = new AccountService();
        msgService = new MessageService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this:: postRegistrationHandler);
        app.post("/login", this:: postLoginRegistrationHandler);
        app.post("/messages", this:: postNewMessageHandler);
        app.get("/messages", this:: getMessageHandler);
        app.get("/messages/{message_id}", this:: getMessageWithIdHandler);
        app.delete("/messages/{message_id}", this:: deleteMessageHandler);
        app.get("/accounts/{account_id}/messages", this:: getMessagesFromAccountHandler);
        app.patch("/messages/{message_id}", this :: updateMessageHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void postRegistrationHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Account acc = mapper.readValue(ctx.body(), Account.class);
        Account addedAcc = accService.registerAccount(acc);
        if(addedAcc == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.json(mapper.writeValueAsString(addedAcc));
            ctx.status(200);
        }

    }
    private void postLoginRegistrationHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Account acc = mapper.readValue(ctx.body(), Account.class);
        Account loggedInAcc = accService.loginUser(acc);
        if(loggedInAcc == null)
        {
            ctx.status(401);
        }
        else
        {
            ctx.json(mapper.writeValueAsString(loggedInAcc));
            ctx.status(200);
        }

    }
    private void postNewMessageHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Message msg = mapper.readValue(ctx.body(), Message.class);
        Message addedMsg = msgService.addMessage(msg);
        if(addedMsg == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.json(mapper.writeValueAsString(addedMsg));
            ctx.status(200);

        }

    }
    private void getMessageHandler(Context ctx)
    {
        ctx.json(msgService.getAllMessages());
        ctx.status(200);

    }
    private void getMessageWithIdHandler(Context ctx)
    {
        int msgId = Integer.parseInt(ctx.pathParam("message_id"));
        Message msg = msgService.getMessageWithId(msgId);
        if(msg == null)
        {
            ctx.status(200);
        }
        else
        {
            ctx.json(msg);
            ctx.status(200);
        }
    }
    private void deleteMessageHandler(Context ctx)
    {
        int msgId = Integer.parseInt(ctx.pathParam("message_id"));
        if(msgService.getMessageWithId(msgId) == null)
        {
            ctx.status(200);
        }
        else
        {
            ctx.json(msgService.deleteMessage(msgId));
            ctx.status(200);
        }
        
    }
    private void getMessagesFromAccountHandler(Context ctx)
    {
        int accId = Integer.parseInt(ctx.pathParam("account_id"));
        ctx.json(msgService.getAllMessagesFromUser(accId));
        ctx.status(200);
    }
    private void updateMessageHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        String msg = mapper.readTree(ctx.body()).get("message_text").asText();
        int msgId = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMsg = msgService.updateMessage(msgId, msg);

        if(updatedMsg == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.json(updatedMsg);
            ctx.status(200);
        }

    }
}