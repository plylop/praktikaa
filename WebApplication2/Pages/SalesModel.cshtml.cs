using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
namespace WebApplication2.Pages
{
    public class Sale
    {
        public int SaleID { get; set; }
        public DateTime Date { get; set; }
        public decimal TotalAmount { get; set; }
        public int ClientID { get; set; }
        public int PharmacyID { get; set; }

        public virtual Client Client { get; set; } // Навигационное свойство для клиента
        public virtual Pharmacy Pharmacy { get; set; } // Навигационное свойство для аптеки
    }
    namespace WebApplication2.Pages
    {
        public class SalesModel : PageModel
        {
            private readonly MyAppDbContext _context;

            public SalesModel(MyAppDbContext context)
            {
                _context = context;
            }

            public IList<Sale> Sales { get; set; } = new List<Sale>();
            public Sale Sale { get; set; }

            public async Task OnGetAsync()
            {
                Sales = await _context.Sale.ToListAsync();
            }

            public async Task<IActionResult> OnPostCreateAsync(Sale sale)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                _context.Sale.Add(sale);
                await _context.SaveChangesAsync();

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostDeleteAsync(int saleID)
            {
                var sale = await _context.Sale.FindAsync(saleID);
                if (sale != null)
                {
                    _context.Sale.Remove(sale);
                    await _context.SaveChangesAsync();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnGetEditAsync(int saleID)
            {
                Sale = await _context.Sale.FindAsync(saleID);
                if (Sale == null)
                {
                    return NotFound();
                }
                return Page();
            }

            public async Task<IActionResult> OnPostEditAsync(Sale sale)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                try
                {
                    _context.Attach(sale).State = EntityState.Modified;
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
                    Sales = await _context.Sale.ToListAsync();
                }
                else
                {
                    Sales = await _context.Sale
                        .Where(s => s.ClientID.ToString().Contains(searchTerm) || s.PharmacyID.ToString().Contains(searchTerm))
                        .ToListAsync();
                }

                return Page();
            }
        }
    }
}


