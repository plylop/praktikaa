using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
namespace WebApplication2.Pages
{

    public class Employee
    {
        public int EmployeeID { get; set; }
        public string FullName { get; set; } // ФИО
        public string Position { get; set; } // Должность
        public int PharmacyID { get; set; } // ID аптеки

        public virtual Pharmacy Pharmacy { get; set; } // Навигационное свойство для аптеки
    }
    namespace WebApplication2.Pages
    {
        public class EmployeesModel : PageModel
        {
            private readonly MyAppDbContext _context;

            public EmployeesModel(MyAppDbContext context)
            {
                _context = context;
            }

            public IList<Employee> Employees { get; set; } = new List<Employee>();
            public Employee Employee { get; set; }

            public async Task OnGetAsync()
            {
                Employees = await _context.Employee
                    .Include(e => e.Pharmacy)
                    .ToListAsync();
            }

            public async Task<IActionResult> OnPostCreateAsync(Employee employee)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                _context.Employee.Add(employee);
                await _context.SaveChangesAsync();

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostDeleteAsync(int employeeID)
            {
                var employee = await _context.Employee.FindAsync(employeeID);
                if (employee != null)
                {
                    _context.Employee.Remove(employee);
                    await _context.SaveChangesAsync();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnGetEditAsync(int employeeID)
            {
                Employee = await _context.Employee.FindAsync(employeeID);
                if (Employee == null)
                {
                    return NotFound();
                }
                return Page();
            }

            public async Task<IActionResult> OnPostEditAsync(Employee employee)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                try
                {
                    _context.Attach(employee).State = EntityState.Modified;
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException)
                {
                    ModelState.AddModelError(string.Empty, "Не удалось обновить данные. Попробуйте еще раз.");
                    return Page();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostSearchAsync(string searchTerm)
            {
                if (string.IsNullOrWhiteSpace(searchTerm))
                {
                    Employees = await _context.Employee
                        .Include(e => e.Pharmacy)
                        .ToListAsync();
                }
                else
                {
                    Employees = await _context.Employee
                        .Include(e => e.Pharmacy)
                        .Where(e => e.FullName.Contains(searchTerm) || e.Position.Contains(searchTerm))
                        .ToListAsync();
                }

                return Page();
            }
        }
    }
}