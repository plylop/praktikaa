using Microsoft.EntityFrameworkCore;
using WebApplication2.Pages;

public class MyAppDbContext : DbContext
{
    public MyAppDbContext(DbContextOptions<MyAppDbContext> options)
        : base(options)
    {
    }

    public DbSet<SoldMedicines> SoldMedicines { get; set; }
    public DbSet<Medicine> Medicine { get; set; }
    public DbSet<Sale> Sale { get; set; }
    public DbSet<Client> Client { get; set; }
    public DbSet<Employee> Employee { get; set; }
    public DbSet<Pharmacy> Pharmacy { get; set; }

}