using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
namespace WebApplication2.Pages
{

    public class Medicine
    {
        public int MedicineID { get; set; }
        public string Name { get; set; }
        public string DosageForm { get; set; } // Форма выпуска
        public decimal Price { get; set; }
        public int QuantityInStock { get; set; } // Количество в наличии
    }
    namespace WebApplication2.Pages
    {
        public class ProductsModel : PageModel
        {
            private readonly MyAppDbContext _context;

            public ProductsModel(MyAppDbContext context)
            {
                _context = context;
            }

            public IList<Medicine> Medicines { get; set; } = new List<Medicine>();
            public Medicine Medicine { get; set; }

            public async Task OnGetAsync()
            {
                Medicines = await _context.Medicine.ToListAsync();
            }

            public async Task<IActionResult> OnPostCreateAsync(Medicine medicine)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                _context.Medicine.Add(medicine);
                await _context.SaveChangesAsync();

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostDeleteAsync(int medicineID)
            {
                var medicine = await _context.Medicine.FindAsync(medicineID);
                if (medicine != null)
                {
                    _context.Medicine.Remove(medicine);
                    await _context.SaveChangesAsync();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnGetEditAsync(int medicineID)
            {
                Medicine = await _context.Medicine.FindAsync(medicineID);
                if (Medicine == null)
                {
                    return NotFound();
                }
                return Page();
            }

            public async Task<IActionResult> OnPostEditAsync(Medicine medicine)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                try
                {
                    _context.Attach(medicine).State = EntityState.Modified;
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
                    Medicines = await _context.Medicine.ToListAsync();
                }
                else
                {
                    Medicines = await _context.Medicine
                        .Where(m => m.Name.Contains(searchTerm) || m.DosageForm.Contains(searchTerm))
                        .ToListAsync();
                }

                return Page();
            }
        }
    }
}