package com.hse;

import com.hse.analytics.*;
import com.hse.command.*;
import com.hse.domain.OperationType;
import com.hse.facade.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankAccountFacade accountFacade = new BankAccountFacade();
        CategoryFacade categoryFacade = new CategoryFacade();
        OperationFacade operationFacade = new OperationFacade(accountFacade);
        AnalyticsService analyticsService = new AnalyticsService(operationFacade, categoryFacade);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Создать счет");
            System.out.println("2. Обновить счет");
            System.out.println("3. Удалить счет");
            System.out.println("4. Создать категорию");
            System.out.println("5. Удалить категорию");
            System.out.println("6. Добавить операцию");
            System.out.println("7. Удалить операцию");
            System.out.println("8. Показать все счета");
            System.out.println("9. Показать все категории");
            System.out.println("10. Показать все операции");
            System.out.println("11. Аналитика (баланс)");
            System.out.println("12. Аналитика (по категориям)");
            System.out.println("13. Выход");
            System.out.print("Введите номер команды: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Введите название счета: ");
                    String accName = scanner.nextLine();
                    System.out.print("Введите начальный баланс: ");
                    double accBalance = scanner.nextDouble();
                    scanner.nextLine();
                    new TimingDecorator(new CreateAccountCommand(accountFacade, accName, accBalance)).execute();
                    break;

                case 2:
                    System.out.print("Введите ID счета: ");
                    String updateAccId = scanner.nextLine();
                    System.out.print("Введите новое имя: ");
                    String newAccName = scanner.nextLine();
                    new TimingDecorator(new UpdateAccountCommand(accountFacade, updateAccId, newAccName)).execute();
                    break;

                case 3:
                    System.out.print("Введите ID счета: ");
                    String delAccId = scanner.nextLine();
                    new TimingDecorator(new DeleteAccountCommand(accountFacade, delAccId)).execute();
                    break;

                case 4:
                    System.out.print("Введите название категории: ");
                    String catName = scanner.nextLine();
                    System.out.print("Введите тип категории (INCOME/EXPENSE): ");
                    String catType = scanner.nextLine().toUpperCase();
                    new TimingDecorator(new CreateCategoryCommand(categoryFacade, catName, OperationType.valueOf(catType))).execute();
                    break;

                case 5:
                    System.out.print("Введите ID категории: ");
                    String delCatId = scanner.nextLine();
                    new TimingDecorator(new DeleteCategoryCommand(categoryFacade, delCatId)).execute();
                    break;

                case 6:
                    System.out.print("Введите ID счета: ");
                    String accId = scanner.nextLine();
                    System.out.print("Введите сумму: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введите тип операции (INCOME/EXPENSE): ");
                    String opType = scanner.nextLine().toUpperCase();
                    System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Введите описание: ");
                    String desc = scanner.nextLine();
                    System.out.print("Введите ID категории: ");
                    String categoryId = scanner.nextLine();
                
                    new TimingDecorator(new AddOperationCommand(operationFacade, 
                        OperationType.valueOf(opType), accId, amount, date, desc, categoryId)).execute();
                    System.out.println("Операция успешно добавлена!");
                    break;

                case 7:
                    System.out.print("Введите ID операции: ");
                    String delOpId = scanner.nextLine();
                    new TimingDecorator(new DeleteOperationCommand(operationFacade, delOpId)).execute();
                    break;

                case 8: // Показать все счета
                    accountFacade.getAllAccounts().forEach(acc -> 
                        System.out.println(acc.getId() + ": " + acc.getName() + " - Баланс: " + acc.getBalance()));
                    break;

                case 9: // Показать все категории
                    categoryFacade.getAllCategories().forEach(cat -> 
                        System.out.println(cat.getId() + ": " + cat.getName() + " (" + cat.getType() + ")"));
                    break;

                case 10: // Показать все операции
                    System.out.println("\nВсе операции:");
                    operationFacade.getOperationsByAccount(null).forEach(op -> 
                        System.out.println(op.getId() + ": " + op.getDescription() + " - " + op.getAmount() + 
                                            " (" + op.getType() + ") | Счет: " + op.getAccountId() + 
                                            " | Категория: " + op.getCategoryId()));
                    break;

                case 11: // Аналитика (баланс)
                    System.out.print("Введите начальную дату (ГГГГ-ММ-ДД): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Введите конечную дату (ГГГГ-ММ-ДД): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    BalanceDifferenceReport balanceReport = analyticsService.getBalanceDifference(startDate, endDate);
                    System.out.println(balanceReport);
                    break;

                case 12: // Аналитика (по категориям)
                    System.out.print("Введите тип операции (INCOME/EXPENSE): ");
                    String categoryOpType = scanner.nextLine().toUpperCase();
                    System.out.print("Введите начальную дату (ГГГГ-ММ-ДД): ");
                    LocalDate startCategoryDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Введите конечную дату (ГГГГ-ММ-ДД): ");
                    LocalDate endCategoryDate = LocalDate.parse(scanner.nextLine());

                    CategoryGroupReport categoryReport = analyticsService.getCategoryReport(
                            OperationType.valueOf(categoryOpType),
                            startCategoryDate,
                            endCategoryDate
                    );
                    System.out.println(categoryReport);
                    break;

                case 13:
                    running = false;
                    break;

                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
        scanner.close();
        System.out.println("Выход из программы.");
    }
}
