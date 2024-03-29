package models;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import concord.Channel;
import concord.ConcordClient;
import concord.DirectConversation;
import concord.Message;
import concord.Server;
import concord.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.ContentController;
import views.CreateAccountController;
import views.DCController;
import views.LoginController;
import views.ServerController;
import views.UserController;

public class ViewTransitionModel implements ViewTransitionModelInterface
{
	BorderPane mainView;
	ConcordModel concordModel;
	ConcordClient client;
	UserViewTransitionModel userModel;
	
	public ViewTransitionModel(BorderPane view, ConcordClient c, ConcordModel cModel)
	{
		mainView = view;
		concordModel = cModel;
		client = c;
	    userModel = new UserViewTransitionModel(view);
	}
	
	@Override
	public void showLogin()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/LoginView.fxml"));
		try
		{
			Pane view = loader.load();
			mainView.setCenter(view);
			LoginController cont = loader.getController();
			cont.setModel(this, client);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void showContent() throws RemoteException
	{		
		concordModel.getServers().clear();
		if (concordModel.getServers().size() == 0)
		{
			ArrayList<Server> serverList;
			try
			{
				serverList = client.getServerByUserId();
				for (Server s: serverList)
				{
					//System.out.println(s.getServerName());
					//Label l = new Label();
					//l.setText(s.getServerName());
					concordModel.getServers().add(s);
					//concordModel.getServers().add(l);
				}
			} catch (RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/ContentAlterView.fxml"));
		try
		{
			BorderPane view = loader.load();
			mainView.setCenter(view);
			ContentController cont = loader.getController();
			cont.setModel(this, concordModel, client);
			showDc();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void showServer(Server s) throws RemoteException
	{
		concordModel.getChannels().clear();
		
		ArrayList<Channel> channelList;
		channelList = s.getChannels();
		for (Channel c: channelList)
		{
			concordModel.getChannels().add(c);
		}
		
		concordModel.getUsers().clear();
		
		ArrayList<User> userList;
		userList = s.getUsers();
		for (User u: userList)
		{
			concordModel.getUsers().add(u);
		}
		
		concordModel.getPinMessages().clear();
		
		ArrayList<Message> pinList;
		pinList = s.getPins();
		System.out.println(pinList);
		for (Message m: pinList)
		{
			concordModel.getPinMessages().add(m);
		}
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/ServerAlterView.fxml"));
		try
		{
			/*SplitPane view = loader.load();
			BorderPane joe = (BorderPane) mainView.lookup("#rightSide");
			joe.setCenter(view);*/
			BorderPane view = loader.load();
			BorderPane content = (BorderPane) mainView.lookup("#contentPane");
			content.setCenter(view);
			ServerController cont = loader.getController();
			cont.setModel(this, concordModel, client, s);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void showDc()
	{
		concordModel.getDcs().clear();
		concordModel.getDcsMessages().clear();
		if (concordModel.getDcsMessages().size() == 0)
		{ 
			ArrayList<DirectConversation> dcList;
			try
			{
				dcList = client.getDcById();
				for (DirectConversation d: dcList)
				{
					concordModel.getDcs().add(d);
					for (Message m: d.getMessages())
					{
						concordModel.getDcsMessages().add(m);
					}
				}
			} catch (RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
			  .getResource("../views/DcAlterView.fxml"));
		try
		{
			BorderPane view = loader.load();
			BorderPane content = (BorderPane) mainView.lookup("#contentPane");
			content.setCenter(view);
			DCController cont = loader.getController();
			cont.setModel(this, concordModel, client);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}

	/**
	 * @return the userModel
	 */
	public UserViewTransitionModel getUserModel()
	{
		return userModel;
	}

	@Override
	public void showUser()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(UserViewTransitionModel.class
				.getResource("../views/UserAlterView.fxml"));
		try
		{
			SplitPane view = loader.load();
			mainView.setCenter(view);
			UserController cont = loader.getController();
			cont.setViewModel(this);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void showCreate()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/CreateAccountView.fxml"));
		try
		{
			BorderPane view = loader.load();
			mainView.setCenter(view);
			CreateAccountController cont = loader.getController();
			cont.setModel(this, client);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}


