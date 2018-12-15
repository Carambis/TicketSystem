package by.tc.web.controller;

import by.tc.web.controller.command.Command;
import by.tc.web.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName,Command> map = new HashMap<>();

    public CommandProvider() {
        map.put(CommandName.AUTHORIZATION, new AuthorizationImpl());
        map.put(CommandName.REGISTRATION, new RegistrationImpl());
        map.put(CommandName.FILL_PAGE_INDEX, new FillPageIndex());
        map.put(CommandName.LOCALIZATION, new LocalPage());
        map.put(CommandName.EXIT_USER,new ExitUser());
        map.put(CommandName.FILL_PAGE_FILMS, new FillPageFilms());
        map.put(CommandName.FILL_PAGE_FILM, new FillPageFilm());
        map.put(CommandName.LOCALIZATION_PAGE_FILM, new LocalPageFilm());
        map.put(CommandName.MARK, new MarkImpl());
        map.put(CommandName.FILL_ADMIN_MENU,new FillAdminMenu());
        map.put(CommandName.ADD_FILM,new AddFilmImpl());
        map.put(CommandName.EDIT_FILM,new EditFIlm());
        map.put(CommandName.DELETE_FILM,new DeleteFilm());
        map.put(CommandName.ADD_USER,new AddUser());
        map.put(CommandName.EDIT_USER,new EditUser());
        map.put(CommandName.DELETE_USER,new DeleteUser());
        map.put(CommandName.DELETE_COMMENT, new DeleteComment());
        map.put(CommandName.BAN_USER,new BanUser());
        map.put(CommandName.EDIT_PROFILE,new EditProfile());
        map.put(CommandName.LOAD_PROFILE,new LoadProfile());
        map.put(CommandName.SEARCH,new Search());
    }

    public Command getCommand(String name){
        CommandName commandName = CommandName.valueOf(name);
        return map.get(commandName);
    }
}
