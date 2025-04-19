package main;

public class CommandHintBar {
    public static void printCommandHintBar() {
    System.out.println(ColorScheme.COMMAND_HINT_COLOR +
    "\n[+] add | [-] remove | [?] find | [L] list | [C] list_client | [R] register | [X] remove_client | [#] count | " +
    "[Y] filter_year | [B] filter_borrow | [↑] sort_asc | [↓] sort_desc | [>] borrow | [<] return | [!] exit" +
    ColorScheme.RESET);
   }
}
